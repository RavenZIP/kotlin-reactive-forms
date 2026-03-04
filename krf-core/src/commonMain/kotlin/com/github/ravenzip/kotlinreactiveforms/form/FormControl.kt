package com.github.ravenzip.kotlinreactiveforms.form

import androidx.compose.runtime.Stable
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.ValueChangeType
import com.github.ravenzip.kotlinreactiveforms.extension.addOrRemove
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
import com.github.ravenzip.kotlinreactiveforms.validation.ValidatorFn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Stable
interface FormControl<TValue, out TError : ValidationError> {
    val value: TValue
    val valueChangeType: ValueChangeType
    val status: FormControlStatus
    val disabled: Boolean
    val touched: Boolean
    val dirty: Boolean
    val errors: List<TError>
    val valueChanges: StateFlow<TValue>
    val valueChangeTypeChanges: StateFlow<ValueChangeType>
    val statusChanges: StateFlow<FormControlStatus>
    val touchedChanges: StateFlow<Boolean>
    val dirtyChanges: StateFlow<Boolean>
    val errorsChanges: StateFlow<List<TError>>
    val hasValidators: Boolean
}

@Stable
interface MutableFormControl<TValue, out TError : ValidationError> : FormControl<TValue, TError> {
    fun setValue(value: TValue)

    fun reset()

    fun reset(value: TValue)

    fun disable()

    fun enable()

    fun markAsTouched()

    fun markAsUntouched()

    fun markAsDirty()

    fun markAsPristine()
}

internal class MutableFormControlImpl<TValue, out TError : ValidationError>(
    private val initialValue: TValue,
    private val initiallyDisabled: Boolean = false,
    private val validators: List<ValidatorFn<TValue, TError>> = emptyList(),
) : MutableFormControl<TValue, TError> {
    private val _disabled: MutableStateFlow<Boolean> = MutableStateFlow(initiallyDisabled)
    private val _touched: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _dirty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _value: MutableStateFlow<TValue> = MutableStateFlow(initialValue)
    private val _errors: MutableStateFlow<List<TError>> = MutableStateFlow(validate())
    private val _status: MutableStateFlow<FormControlStatus> = MutableStateFlow(calculateStatus())
    private val _valueChangeType: MutableStateFlow<ValueChangeType> =
        MutableStateFlow(ValueChangeType.Initialize)

    override val errorsChanges: StateFlow<List<TError>> = _errors.asStateFlow()
    override val valueChanges: StateFlow<TValue> = _value.asStateFlow()
    override val valueChangeTypeChanges: StateFlow<ValueChangeType> = _valueChangeType.asStateFlow()
    override val statusChanges: StateFlow<FormControlStatus> = _status.asStateFlow()
    override val touchedChanges: StateFlow<Boolean> = _touched.asStateFlow()
    override val dirtyChanges: StateFlow<Boolean> = _dirty.asStateFlow()

    override val value: TValue
        get() = _value.value

    override val valueChangeType: ValueChangeType
        get() = _valueChangeType.value

    override val status: FormControlStatus
        get() = statusChanges.value

    override val disabled: Boolean
        get() = _disabled.value

    override val touched: Boolean
        get() = _touched.value

    override val dirty: Boolean
        get() = _dirty.value

    override val errors: List<TError>
        get() = errorsChanges.value

    override val hasValidators: Boolean = validators.count() > 0

    override fun setValue(value: TValue) {
        _value.update { value }
        _valueChangeType.update { ValueChangeType.Set }
        _errors.update { validate() }
        _status.update { calculateStatus() }
    }

    override fun reset() = reset(initialValue)

    override fun reset(value: TValue) {
        _value.update { value }
        _valueChangeType.update { ValueChangeType.Reset }
        _disabled.update { initiallyDisabled }
        _touched.update { false }
        _dirty.update { false }
        _errors.update { validate() }
        _status.update { calculateStatus() }
    }

    override fun disable() {
        _disabled.update { true }
        _status.update { calculateStatus() }
        _errors.update { emptyList() }
    }

    override fun enable() {
        _disabled.update { false }
        _status.update { calculateStatus() }
        _errors.update { validate() }
    }

    override fun markAsTouched() = _touched.update { true }

    override fun markAsUntouched() = _touched.update { false }

    override fun markAsDirty() = _dirty.update { true }

    override fun markAsPristine() = _dirty.update { false }

    private fun validate(): List<TError> =
        validators.mapNotNull { validatorFn -> validatorFn(_value.value) }

    private fun calculateStatus(): FormControlStatus =
        when {
            _disabled.value -> FormControlStatus.Disabled
            _errors.value.isNotEmpty() -> FormControlStatus.Invalid(_errors.value)
            else -> FormControlStatus.Valid
        }
}

fun <TValue, TError : ValidationError> mutableFormControl(
    initialValue: TValue,
    initiallyDisabled: Boolean = false,
    validators: List<ValidatorFn<TValue, TError>>,
): MutableFormControl<TValue, TError> =
    MutableFormControlImpl(initialValue, initiallyDisabled, validators)

fun <TValue> mutableFormControl(
    initialValue: TValue,
    initiallyDisabled: Boolean = false,
): MutableFormControl<TValue, ValidationError> =
    MutableFormControlImpl(initialValue, initiallyDisabled, emptyList())

fun <TValue, TError : ValidationError> MutableFormControl<TValue, TError>.asReadonly():
    FormControl<TValue, TError> = object : FormControl<TValue, TError> by this {}

fun <TValue, TError : ValidationError, TKey> MutableFormControl<List<TValue>, TError>.mergeValue(
    value: TValue,
    keySelector: (TValue) -> TKey,
) {
    val currentValues = this.value.addOrRemove(value, keySelector)
    setValue(currentValues)
}

fun <TValue, TError : ValidationError> MutableFormControl<List<TValue>, TError>.setValue(
    vararg values: TValue
) = setValue(values.toList())

fun <TValue, TError : ValidationError> MutableFormControl<List<TValue>, TError>.reset(
    vararg values: TValue
) = reset(values.toList())
