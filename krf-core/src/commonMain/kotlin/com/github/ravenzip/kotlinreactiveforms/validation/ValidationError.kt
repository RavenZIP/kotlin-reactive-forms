package com.github.ravenzip.kotlinreactiveforms.validation

interface ValidationError {
    val kind: String
    val message: String
}

data class DefaultValidationError(override val kind: String, override val message: String) :
    ValidationError
