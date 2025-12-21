package com.github.ravenzip.kotlinreactiveforms.data

import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

sealed class FormControlStatus {
    object Disabled : FormControlStatus()

    data class Invalid(val errors: List<ValidationError>) : FormControlStatus()

    object Valid : FormControlStatus()
}

fun FormControlStatus.isEnabled(): Boolean = this !is FormControlStatus.Disabled

fun FormControlStatus.isDisabled(): Boolean = this is FormControlStatus.Disabled

fun FormControlStatus.isInvalid(): Boolean = this is FormControlStatus.Invalid

fun FormControlStatus.isValid(): Boolean = this is FormControlStatus.Valid
