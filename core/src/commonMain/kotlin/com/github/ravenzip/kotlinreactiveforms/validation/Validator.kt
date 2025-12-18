package com.github.ravenzip.kotlinreactiveforms.validation

typealias ValidatorFn<T> = (T) -> String?

/** Возможные валидаторы для компонентов */
// TODO надо ли разделить на разные классы согласно типу T?
class Validator {
    companion object {
        val required: ValidatorFn<String> = { value: String ->
            if (value.isEmpty()) "Поле обязательно для заполнения" else null
        }

        fun minLength(min: Int): ValidatorFn<String> = { value: String ->
            if (value.length < min) "Минимальная длина $min символа" else null
        }

        fun maxLength(max: Int): ValidatorFn<String> = { value: String ->
            if (value.length > max) "Максимальная длина $max символа" else null
        }

        fun min(min: Double): ValidatorFn<Double> = { value: Double ->
            if (value < min) "Минимальное допустимое значение $min" else null
        }

        fun max(max: Double): ValidatorFn<Double> = { value: Double ->
            if (value > max) "Максимальное допустимое значение $max" else null
        }

        val email: ValidatorFn<String> = { value: String ->
            if (!emailRegex.matches(value)) "Введен некорректный email" else null
        }

        val phone: ValidatorFn<String> = { value: String ->
            if (!phoneRegex.matches(value)) "Введен некорректный номер телефона" else null
        }
    }
}
