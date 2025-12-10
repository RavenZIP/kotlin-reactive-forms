package form

import androidx.compose.runtime.Stable
import data.FormControlStatus
import data.ValueChangeType
import extension.addOrRemove
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import validation.ValidatorFn

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
    val typeChanges: StateFlow<ValueChangeType>
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
    private val _initialValue: T,
    private val _initiallyDisabled: Boolean = false,
    private val _validators: List<ValidatorFn<T>> = emptyList(),
    private val _coroutineScope: CoroutineScope,
) : MutableFormControl<T> {
    private val _disabled: MutableStateFlow<Boolean> = MutableStateFlow(_initiallyDisabled)
    private val _touched: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _dirty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _valueChanges: MutableStateFlow<T> = MutableStateFlow(_initialValue)
    private val _typeChanges: MutableStateFlow<ValueChangeType> =
        MutableStateFlow(ValueChangeType.Initialize)

    override val errorMessagesChanges: StateFlow<List<String>> =
        _valueChanges
            .map { value -> _validators.mapNotNull { validatorFn -> validatorFn(value) } }
            .stateIn(
                scope = _coroutineScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList(),
            )

    override val valueChanges: StateFlow<T> = _valueChanges.asStateFlow()

    override val typeChanges: StateFlow<ValueChangeType> = _typeChanges.asStateFlow()

    override val statusChanges: StateFlow<FormControlStatus> =
        combine(_disabled, errorMessagesChanges) { disabled, errorMessages ->
                when {
                    disabled -> FormControlStatus.Disabled
                    errorMessages.isNotEmpty() -> FormControlStatus.Invalid(errorMessages)
                    else -> FormControlStatus.Valid
                }
            }
            .stateIn(
                scope = _coroutineScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = FormControlStatus.Valid,
            )

    override val touchedChanges: StateFlow<Boolean> = _touched.asStateFlow()
    override val dirtyChanges: StateFlow<Boolean> = _dirty.asStateFlow()

    override val value: T
        get() = _valueChanges.value

    override val typeChange: ValueChangeType
        get() = _typeChanges.value

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
        _valueChanges.update { value }
        _typeChanges.update { ValueChangeType.Set }
        _dirty.update { true }
    }

    override fun reset() = reset(_initialValue)

    override fun reset(value: T) {
        _valueChanges.update { value }
        _typeChanges.update { ValueChangeType.Reset }
        _disabled.update { _initiallyDisabled }
        _touched.update { false }
        _dirty.update { false }
    }

    override fun markAsTouched() = _touched.update { true }

    override fun markAsUntouched() = _touched.update { false }

    override fun markAsDirty() = _dirty.update { true }

    override fun markAsPristine() = _dirty.update { false }
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
