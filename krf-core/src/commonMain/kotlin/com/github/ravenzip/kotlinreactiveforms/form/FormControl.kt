package com.github.ravenzip.kotlinreactiveforms.form

import androidx.compose.runtime.Stable
import com.github.ravenzip.kotlinreactiveforms.data.FormControlState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.ValueChangeType
import com.github.ravenzip.kotlinreactiveforms.data.disabled
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
    val status: FormControlStatus<TError>
    val touched: Boolean
    val dirty: Boolean
    val stateChanges: StateFlow<FormControlState<TValue, TError>>
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

// TODO Возможно, что disabled все-таки должно быть отдельным полем
// Текущая реализация может сыграть злую шутку, если появится статус Pending и асинхронные
// валидаторы
internal class MutableFormControlImpl<TValue, out TError : ValidationError>(
    private val initialValue: TValue,
    private val disabled: Boolean = false,
    private val validators: List<ValidatorFn<TValue, TError>> = emptyList(),
) : MutableFormControl<TValue, TError> {
    private val _state: MutableStateFlow<FormControlState<TValue, TError>> =
        MutableStateFlow(
            FormControlState(
                value = initialValue,
                status = computeStatus(initialValue, disabled),
            )
        )

    override val value: TValue
        get() = _state.value.value

    override val valueChangeType: ValueChangeType
        get() = _state.value.valueChangeType

    override val status: FormControlStatus<TError>
        get() = _state.value.status

    override val touched: Boolean
        get() = _state.value.touched

    override val dirty: Boolean
        get() = _state.value.dirty

    override val stateChanges: StateFlow<FormControlState<TValue, TError>> = _state.asStateFlow()

    override fun setValue(value: TValue) {
        _state.update { state ->
            state.copy(
                value = value,
                valueChangeType = ValueChangeType.Set,
                status = computeStatus(value, state.status.disabled),
            )
        }
    }

    override fun reset() = reset(initialValue)

    override fun reset(value: TValue) {
        _state.update { state ->
            state.copy(
                value = value,
                valueChangeType = ValueChangeType.Reset,
                status = computeStatus(value, disabled),
                touched = false,
                dirty = false,
            )
        }
    }

    override fun disable() {
        _state.update { state -> state.copy(status = FormControlStatus.Disabled) }
    }

    override fun enable() {
        _state.update { current -> current.copy(status = computeStatus(current.value)) }
    }

    override fun markAsTouched() = _state.update { state -> state.copy(touched = true) }

    override fun markAsUntouched() = _state.update { state -> state.copy(touched = false) }

    override fun markAsDirty() = _state.update { state -> state.copy(dirty = true) }

    override fun markAsPristine() = _state.update { state -> state.copy(dirty = false) }

    private fun computeStatus(value: TValue, disabled: Boolean = false): FormControlStatus<TError> {
        if (disabled) {
            return FormControlStatus.Disabled
        }

        val errors = validate(value)
        if (errors.isNotEmpty()) {
            return FormControlStatus.Invalid(errors)
        }

        return FormControlStatus.Valid
    }

    private fun validate(value: TValue): List<TError> = validators.mapNotNull { validatorFn ->
        validatorFn(value)
    }
}

fun <TValue, TError : ValidationError> mutableFormControl(
    initialValue: TValue,
    disabled: Boolean = false,
    validators: List<ValidatorFn<TValue, TError>>,
): MutableFormControl<TValue, TError> = MutableFormControlImpl(initialValue, disabled, validators)

fun <TValue> mutableFormControl(
    initialValue: TValue,
    disabled: Boolean = false,
): MutableFormControl<TValue, ValidationError> =
    MutableFormControlImpl(initialValue, disabled, emptyList())

fun <TValue, TError : ValidationError> MutableFormControl<TValue, TError>.asReadonly():
    FormControl<TValue, TError> = object : FormControl<TValue, TError> by this {}

fun <TValue, TError : ValidationError> MutableFormControl<List<TValue>, TError>.setValue(
    vararg values: TValue
) = setValue(values.toList())

fun <TValue, TError : ValidationError> MutableFormControl<List<TValue>, TError>.reset(
    vararg values: TValue
) = reset(values.toList())
