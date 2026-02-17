package com.github.ravenzip.kotlinreactiveforms.validation

typealias ValidatorFn<T> = (T) -> ValidationError?

data class ValidationError(val kind: String, val message: String)

/** Возможные валидаторы для компонентов */
// TODO надо ли разделить на разные классы согласно типу T?
// TODO добавить перевод на другие языки в сообщении валидаторов
class Validator {
    companion object {
        val required: ValidatorFn<String> = { value: String ->
            if (value.isEmpty())
                ValidationError(ValidatorName.REQUIRED.name, "Поле обязательно для заполнения")
            else null
        }

        fun minLength(min: Int): ValidatorFn<String> = { value: String ->
            if (value.length < min)
                ValidationError(ValidatorName.MIN_LENGTH.name, "Минимальная длина $min символа")
            else null
        }

        fun maxLength(max: Int): ValidatorFn<String> = { value: String ->
            if (value.length > max)
                ValidationError(ValidatorName.MAX_LENGTH.name, "Максимальная длина $max символа")
            else null
        }

        fun min(min: Int): ValidatorFn<Int> = { value: Int ->
            if (value < min)
                ValidationError(ValidatorName.MIN.name, "Минимальное допустимое значение $min")
            else null
        }

        fun min(min: Double): ValidatorFn<Double> = { value: Double ->
            if (value < min)
                ValidationError(ValidatorName.MIN.name, "Минимальное допустимое значение $min")
            else null
        }

        fun max(max: Int): ValidatorFn<Int> = { value: Int ->
            if (value > max)
                ValidationError(ValidatorName.MAX.name, "Максимальное допустимое значение $max")
            else null
        }

        fun max(max: Double): ValidatorFn<Double> = { value: Double ->
            if (value > max)
                ValidationError(ValidatorName.MAX.name, "Максимальное допустимое значение $max")
            else null
        }

        val email: ValidatorFn<String> = { value: String ->
            if (!emailRegex.matches(value))
                ValidationError(ValidatorName.EMAIL.name, "Введен некорректный email")
            else null
        }

        val phone: ValidatorFn<String> = { value: String ->
            if (!phoneRegex.matches(value))
                ValidationError(ValidatorName.PHONE.name, "Введен некорректный номер телефона")
            else null
        }
    }
}
