package com.github.ravenzip.kotlinreactiveforms.validation

sealed interface ValidationError

data object RequiredValidationError : ValidationError

data class MinValidationError<T>(val min: T, val actual: T) : ValidationError

data class MaxValidationError<T>(val max: T, val actual: T) : ValidationError

data class RangeValidationError<T>(val min: T, val max: T, val actual: T) : ValidationError

data class MinLengthValidationError(val min: Int, val actual: Int) : ValidationError

data class MaxLengthValidationError(val max: Int, val actual: Int) : ValidationError

data object EmailValidationError : ValidationError

data object PhoneValidationError : ValidationError
