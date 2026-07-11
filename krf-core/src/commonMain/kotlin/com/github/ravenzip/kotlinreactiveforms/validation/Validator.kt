package com.github.ravenzip.kotlinreactiveforms.validation

import com.github.ravenzip.kotlinreactiveforms.utils.emailRegex
import com.github.ravenzip.kotlinreactiveforms.utils.phoneRegex

fun interface ValidatorFn<TValue, out TError : ValidationError> {
    operator fun invoke(value: TValue): TError?
}

/** Возможные валидаторы для компонентов */
// TODO надо ли разделить на разные классы согласно типу T?
// TODO добавить перевод на другие языки в сообщении валидаторов
class Validator {
    companion object {
        val required: ValidatorFn<String, DefaultValidationError> = { value: String ->
            if (value.isEmpty())
                DefaultValidationError(
                    kind = ValidatorName.REQUIRED.name,
                    message = "Поле обязательно для заполнения",
                )
            else null
        }

        fun minLength(min: Int): ValidatorFn<String, DefaultValidationError> = { value: String ->
            if (value.length < min)
                DefaultValidationError(
                    kind = ValidatorName.MIN_LENGTH.name,
                    message = "Минимальная длина $min символа",
                )
            else null
        }

        fun maxLength(max: Int): ValidatorFn<String, DefaultValidationError> = { value: String ->
            if (value.length > max)
                DefaultValidationError(
                    kind = ValidatorName.MAX_LENGTH.name,
                    message = "Максимальная длина $max символа",
                )
            else null
        }

        fun min(min: Int): ValidatorFn<Int, DefaultValidationError> = { value: Int ->
            if (value < min)
                DefaultValidationError(
                    kind = ValidatorName.MIN.name,
                    message = "Минимальное допустимое значение $min",
                )
            else null
        }

        fun min(min: Double): ValidatorFn<Double, DefaultValidationError> = { value: Double ->
            if (value < min)
                DefaultValidationError(
                    kind = ValidatorName.MIN.name,
                    message = "Минимальное допустимое значение $min",
                )
            else null
        }

        fun max(max: Int): ValidatorFn<Int, DefaultValidationError> = { value: Int ->
            if (value > max)
                DefaultValidationError(
                    kind = ValidatorName.MAX.name,
                    message = "Максимальное допустимое значение $max",
                )
            else null
        }

        fun max(max: Double): ValidatorFn<Double, DefaultValidationError> = { value: Double ->
            if (value > max)
                DefaultValidationError(
                    kind = ValidatorName.MAX.name,
                    message = "Максимальное допустимое значение $max",
                )
            else null
        }

        val email: ValidatorFn<String, DefaultValidationError> = { value: String ->
            if (!emailRegex.matches(value))
                DefaultValidationError(
                    kind = ValidatorName.EMAIL.name,
                    message = "Введен некорректный email",
                )
            else null
        }

        val phone: ValidatorFn<String, DefaultValidationError> = { value: String ->
            if (!phoneRegex.matches(value))
                DefaultValidationError(
                    kind = ValidatorName.PHONE.name,
                    message = "Введен некорректный номер телефона",
                )
            else null
        }
    }
}
