package com.github.ravenzip.kotlinreactiveforms.form

import androidx.compose.runtime.Stable
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.ValueChangeType
import com.github.ravenzip.kotlinreactiveforms.extension.addOrRemove
import com.github.ravenzip.kotlinreactiveforms.validation.ValidatorFn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

@Stable
interface FormControl<T> {
    val value: T
    val typeChange: ValueChangeType
    val status: FormControlStatus
    val disabled: Boolean
    val touched: Boolean
    val dirty: Boolean
    val errorMessages: List<String>
    val valueChanges: StateFlow<T>
    val valueChangeTypeChanges: StateFlow<ValueChangeType>
    val statusChanges: StateFlow<FormControlStatus>
    val touchedChanges: StateFlow<Boolean>
    val dirtyChanges: StateFlow<Boolean>
    val errorMessagesChanges: StateFlow<List<String>>
}

@Stable
interface MutableFormControl<T> : FormControl<T> {
    fun setValue(value: T)

    fun reset()

    fun reset(value: T)

    fun markAsTouched()

    fun markAsUntouched()

    fun markAsDirty()

    fun markAsPristine()
}

internal class MutableFormControlImpl<T>(
    private val initialValue: T,
    private val initiallyDisabled: Boolean = false,
    private val validators: List<ValidatorFn<T>> = emptyList(),
    coroutineScope: CoroutineScope,
) : MutableFormControl<T> {
    private val _disabled: MutableStateFlow<Boolean> = MutableStateFlow(initiallyDisabled)
    private val _touched: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _dirty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _value: MutableStateFlow<T> = MutableStateFlow(initialValue)
    private val _valueChangeType: MutableStateFlow<ValueChangeType> =
        MutableStateFlow(ValueChangeType.Initialize)

    override val errorMessagesChanges: StateFlow<List<String>> =
        _value
            .map { value -> validate(value) }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Eagerly,
                initialValue = validate(_value.value),
            )

    override val valueChanges: StateFlow<T> = _value.asStateFlow()

    override val valueChangeTypeChanges: StateFlow<ValueChangeType> = _valueChangeType.asStateFlow()

    override val statusChanges: StateFlow<FormControlStatus> =
        combine(_disabled, errorMessagesChanges) { disabled, errorMessages ->
                calculateStatus(disabled, errorMessages)
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Eagerly,
                initialValue = calculateStatus(_disabled.value, errorMessagesChanges.value),
            )

    override val touchedChanges: StateFlow<Boolean> = _touched.asStateFlow()
    override val dirtyChanges: StateFlow<Boolean> = _dirty.asStateFlow()

    override val value: T
        get() = _value.value

    override val typeChange: ValueChangeType
        get() = _valueChangeType.value

    override val status: FormControlStatus
        get() = statusChanges.value

    override val disabled: Boolean
        get() = _disabled.value

    override val touched: Boolean
        get() = _touched.value

    override val dirty: Boolean
        get() = _dirty.value

    override val errorMessages: List<String>
        get() = errorMessagesChanges.value

    override fun setValue(value: T) {
        _value.update { value }
        _valueChangeType.update { ValueChangeType.Set }
    }

    override fun reset() = reset(initialValue)

    override fun reset(value: T) {
        _value.update { value }
        _valueChangeType.update { ValueChangeType.Reset }
        _disabled.update { initiallyDisabled }
        _touched.update { false }
        _dirty.update { false }
    }

    override fun markAsTouched() = _touched.update { true }

    override fun markAsUntouched() = _touched.update { false }

    override fun markAsDirty() = _dirty.update { true }

    override fun markAsPristine() = _dirty.update { false }

    private fun validate(value: T): List<String> =
        validators.mapNotNull { validatorFn -> validatorFn(value) }

    private fun calculateStatus(disabled: Boolean, errorMessages: List<String>): FormControlStatus =
        when {
            disabled -> FormControlStatus.Disabled
            errorMessages.isNotEmpty() -> FormControlStatus.Invalid(errorMessages)
            else -> FormControlStatus.Valid
        }
}

fun <T> mutableFormControl(
    initialValue: T,
    initiallyDisabled: Boolean = false,
    validators: List<ValidatorFn<T>> = emptyList(),
    coroutineScope: CoroutineScope,
): MutableFormControl<T> =
    MutableFormControlImpl(initialValue, initiallyDisabled, validators, coroutineScope)

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
