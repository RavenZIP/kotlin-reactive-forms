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
interface FormControl<T> {
    val value: T
    val valueChangeType: ValueChangeType
    val status: FormControlStatus
    val disabled: Boolean
    val touched: Boolean
    val dirty: Boolean
    val errors: List<ValidationError>
    val valueChanges: StateFlow<T>
    val valueChangeTypeChanges: StateFlow<ValueChangeType>
    val statusChanges: StateFlow<FormControlStatus>
    val touchedChanges: StateFlow<Boolean>
    val dirtyChanges: StateFlow<Boolean>
    val errorsChanges: StateFlow<List<ValidationError>>
}

@Stable
interface MutableFormControl<T> : FormControl<T> {
    fun setValue(value: T)

    fun reset()

    fun reset(value: T)

    fun disable()

    fun enable()

    fun markAsTouched()

    fun markAsUntouched()

    fun markAsDirty()

    fun markAsPristine()
}

internal class MutableFormControlImpl<T>(
    private val initialValue: T,
    private val initiallyDisabled: Boolean = false,
    private val validators: List<ValidatorFn<T>> = emptyList(),
) : MutableFormControl<T> {
    private val _disabled: MutableStateFlow<Boolean> = MutableStateFlow(initiallyDisabled)
    private val _touched: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _dirty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _value: MutableStateFlow<T> = MutableStateFlow(initialValue)
    private val _errors: MutableStateFlow<List<ValidationError>> = MutableStateFlow(validate())
    private val _status: MutableStateFlow<FormControlStatus> = MutableStateFlow(calculateStatus())
    private val _valueChangeType: MutableStateFlow<ValueChangeType> =
        MutableStateFlow(ValueChangeType.Initialize)

    override val errorsChanges: StateFlow<List<ValidationError>> = _errors.asStateFlow()
    override val valueChanges: StateFlow<T> = _value.asStateFlow()
    override val valueChangeTypeChanges: StateFlow<ValueChangeType> = _valueChangeType.asStateFlow()
    override val statusChanges: StateFlow<FormControlStatus> = _status.asStateFlow()
    override val touchedChanges: StateFlow<Boolean> = _touched.asStateFlow()
    override val dirtyChanges: StateFlow<Boolean> = _dirty.asStateFlow()

    override val value: T
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

    override val errors: List<ValidationError>
        get() = errorsChanges.value

    override fun setValue(value: T) {
        _value.update { value }
        _valueChangeType.update { ValueChangeType.Set }
        _errors.update { validate() }
        _status.update { calculateStatus() }
    }

    override fun reset() = reset(initialValue)

    override fun reset(value: T) {
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
    }

    override fun enable() {
        _disabled.update { false }
        _status.update { calculateStatus() }
    }

    override fun markAsTouched() = _touched.update { true }

    override fun markAsUntouched() = _touched.update { false }

    override fun markAsDirty() = _dirty.update { true }

    override fun markAsPristine() = _dirty.update { false }

    private fun validate(): List<ValidationError> =
        validators.mapNotNull { validatorFn -> validatorFn(_value.value) }

    private fun calculateStatus(): FormControlStatus =
        when {
            _disabled.value -> FormControlStatus.Disabled
            _errors.value.isNotEmpty() -> FormControlStatus.Invalid(_errors.value)
            else -> FormControlStatus.Valid
        }
}

fun <T> mutableFormControl(
    initialValue: T,
    initiallyDisabled: Boolean = false,
    validators: List<ValidatorFn<T>> = emptyList(),
): MutableFormControl<T> = MutableFormControlImpl(initialValue, initiallyDisabled, validators)

fun <T> MutableFormControl<T>.asReadonly(): FormControl<T> = object : FormControl<T> by this {}

fun <T, K> MutableFormControl<List<T>>.mergeValue(value: T, keySelector: (T) -> K) {
    val currentValues = this.value.addOrRemove(value, keySelector)
    setValue(currentValues)
}

fun <T> MutableFormControl<List<T>>.setValue(vararg values: T) {
    setValue(values.toList())
}

fun <T> MutableFormControl<List<T>>.reset(vararg values: T) {
    reset(values.toList())
}
