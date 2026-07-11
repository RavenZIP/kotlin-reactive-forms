package com.github.ravenzip.kotlinreactiveforms.form

import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface FormControlKey<TValue, out TError : ValidationError>

interface FormGroup<TValue, out TError : ValidationError> {
    /**
     * Возвращает типизированный контрол по указанному ключу из общего списка
     *
     * В случае отсутствия контрола по указанному ключу, возникнет исключение
     * [IllegalArgumentException]
     */
    fun <T : Any, TError : ValidationError> getControl(
        key: FormControlKey<T, TError>
    ): MutableFormControl<T, TError>

    val errors: Map<FormControlKey<*, *>, List<TError>>
}

interface MutableFormGroup<
    TValue,
    out TError : ValidationError,
> : FormGroup<TValue, TError> {
    fun setValue(value: TValue)

    fun patchValue(value: Map<FormControlKey<*, *>, Any>)

    fun reset()

    fun reset(value: TValue)

    fun enable()

    fun disable()
}

private class MutableFormGroupImpl<
    TValue,
    out TError : ValidationError,
>(val controls: Map<FormControlKey<*, *>, MutableFormControl<*, TError>>) :
    MutableFormGroup<TValue, TError> {
    private val _errors = MutableStateFlow(emptyList<TError>())

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any, TError : ValidationError> getControl(
        key: FormControlKey<T, TError>
    ): MutableFormControl<T, TError> {
        val untypedControl =
            controls[key] ?: throw IllegalArgumentException("Control $key not found")

        return untypedControl as MutableFormControl<T, TError>
    }

    override val errors: Map<FormControlKey<*, *>, List<TError>>
        get() = controls.mapValues { x ->
            x.value.errors
        }

    override fun setValue(value: TValue) {
        TODO("Not yet implemented")
    }

    @Suppress("UNCHECKED_CAST")
    override fun patchValue(value: Map<FormControlKey<*, *>, Any>) {
        value.forEach { (key, value) ->
            val control = controls[key] ?: return@forEach

            val currentClass = control.value?.let { it::class }
            if (currentClass == null || !currentClass.isInstance(value)) {
                return@forEach
            }

            val typedControl = control as MutableFormControl<Any, *>
            typedControl.setValue(value)
        }
    }

    override fun reset() = controls.values.forEach { control -> control.reset() }

    override fun reset(value: TValue) {
        TODO("Not yet implemented")
    }

    override fun enable() = controls.values.forEach { control -> control.enable() }

    override fun disable() = controls.values.forEach { control -> control.disable() }
}

// Проверка реализации
sealed interface MyFormGroup {
    object Id : FormControlKey<Int, ValidationError>
}

fun t(f: MutableFormGroup<*, ValidationError>) {
    val t = f.getControl(MyFormGroup.Id)

    f.patchValue(mapOf(MyFormGroup.Id to "2"))

    val controlValue = t.value
}
