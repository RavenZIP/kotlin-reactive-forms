package com.github.ravenzip.kotlinreactiveforms.data

import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

sealed class FormControlStatus<out TError : ValidationError> {
    object Disabled : FormControlStatus<Nothing>()

    data class Invalid<TError : ValidationError>(val errors: List<TError>) :
        FormControlStatus<TError>()

    object Valid : FormControlStatus<Nothing>()
}

val <TError : ValidationError> FormControlStatus<TError>.enabled: Boolean
    get() = this !is FormControlStatus.Disabled

val <TError : ValidationError> FormControlStatus<TError>.disabled: Boolean
    get() = this is FormControlStatus.Disabled

val <TError : ValidationError> FormControlStatus<TError>.invalid: Boolean
    get() = this is FormControlStatus.Invalid

val <TError : ValidationError> FormControlStatus<TError>.valid: Boolean
    get() = this is FormControlStatus.Valid
