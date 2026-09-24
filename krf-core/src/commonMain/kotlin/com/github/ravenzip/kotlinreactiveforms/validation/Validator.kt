package com.github.ravenzip.kotlinreactiveforms.validation

import com.github.ravenzip.kotlinreactiveforms.utils.emailRegex
import com.github.ravenzip.kotlinreactiveforms.utils.phoneRegex

fun interface ValidatorFn<TValue, TError : ValidationError> {
    operator fun invoke(value: TValue): TError?
}

// TODO возвращать не сообщение, а структуру примерно как в ангуляре
object RequiredValidator {
    fun string(): ValidatorFn<String, ValidationError> = { value ->
        if (value.isEmpty()) createError() else null
    }

    fun <T> collection(): ValidatorFn<Collection<T>, ValidationError> = { value ->
        if (value.isEmpty()) createError() else null
    }

    fun <T> value(): ValidatorFn<T?, ValidationError> = { value ->
        if (value == null) createError() else null
    }

    private fun createError() =
        ValidationErrorImpl(
            kind = ValidatorName.REQUIRED.name,
            message = "Поле обязательно для заполнения",
        )
}

object LengthValidator {
    fun min(min: Int): ValidatorFn<String, ValidationError> = { value ->
        if (value.length < min)
            ValidationErrorImpl(
                kind = ValidatorName.MIN_LENGTH.name,
                message = "Минимальная длина $min символа",
            )
        else null
    }

    fun max(max: Int): ValidatorFn<String, ValidationError> = { value: String ->
        if (value.length > max)
            ValidationErrorImpl(
                kind = ValidatorName.MAX_LENGTH.name,
                message = "Максимальная длина $max символа",
            )
        else null
    }
}

object RangeValidator {
    fun <T : Comparable<T>> min(min: T): ValidatorFn<T, ValidationError> = { value ->
        if (value < min)
            ValidationErrorImpl(
                kind = ValidatorName.MIN.name,
                message = "Минимальное допустимое значение $min",
            )
        else null
    }

    fun <T : Comparable<T>> max(max: T): ValidatorFn<T, ValidationError> = { value ->
        if (value > max)
            ValidationErrorImpl(
                kind = ValidatorName.MAX.name,
                message = "Максимальное допустимое значение $max",
            )
        else null
    }

    fun <T : Comparable<T>> range(min: T, max: T): ValidatorFn<T, ValidationError> = { value ->
        if (value !in min..max)
            ValidationErrorImpl(
                kind = ValidatorName.MAX.name,
                message = "Значение выходит за рамки диапазона ($min-$max)",
            )
        else null
    }
}

object EmailValidator {
    val validator: ValidatorFn<String, ValidationError> = { value ->
        if (!emailRegex.matches(value))
            ValidationErrorImpl(
                kind = ValidatorName.EMAIL.name,
                message = "Введен некорректный email",
            )
        else null
    }
}

object PhoneValidator {
    val validator: ValidatorFn<String, ValidationError> = { value: String ->
        if (!phoneRegex.matches(value))
            ValidationErrorImpl(
                kind = ValidatorName.PHONE.name,
                message = "Введен некорректный номер телефона",
            )
        else null
    }
}
