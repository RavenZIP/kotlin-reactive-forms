package com.github.ravenzip.kotlin.reactiveforms.validation

import com.github.ravenzip.kotlin.reactiveforms.utils.emailRegex
import com.github.ravenzip.kotlin.reactiveforms.utils.phoneRegex

fun interface ValidatorFn<TValue, out TError : ValidationError> {
    operator fun invoke(value: TValue): TError?
}

object RequiredValidator {
    fun string(): ValidatorFn<String, RequiredValidationError> = { value ->
        if (value.isEmpty()) RequiredValidationError else null
    }

    fun <T> collection(): ValidatorFn<Collection<T>, RequiredValidationError> = { value ->
        if (value.isEmpty()) RequiredValidationError else null
    }

    fun <T> value(): ValidatorFn<T?, RequiredValidationError> = { value ->
        if (value == null) RequiredValidationError else null
    }
}

object LengthValidator {
    fun min(min: Int): ValidatorFn<String, MinLengthValidationError> = { value ->
        if (value.length < min) MinLengthValidationError(min, value.length) else null
    }

    fun max(max: Int): ValidatorFn<String, MaxLengthValidationError> = { value: String ->
        if (value.length > max) MaxLengthValidationError(max, value.length) else null
    }
}

object RangeValidator {
    fun <T : Comparable<T>> min(min: T): ValidatorFn<T, MinValidationError<T>> = { value ->
        if (value < min) MinValidationError(min, value) else null
    }

    fun <T : Comparable<T>> max(max: T): ValidatorFn<T, MaxValidationError<T>> = { value ->
        if (value > max) MaxValidationError(max, value) else null
    }

    fun <T : Comparable<T>> range(min: T, max: T): ValidatorFn<T, RangeValidationError<T>> =
        { value ->
            if (value !in min..max) RangeValidationError(min, max, value) else null
        }
}

object EmailValidator {
    val validator: ValidatorFn<String, EmailValidationError> = { value ->
        if (!emailRegex.matches(value)) EmailValidationError else null
    }
}

object PhoneValidator {
    val validator: ValidatorFn<String, PhoneValidationError> = { value: String ->
        if (!phoneRegex.matches(value)) PhoneValidationError else null
    }
}
