package com.github.ravenzip.kotlinreactiveforms.data

sealed class FormControlStatus {
    object Disabled : FormControlStatus()

    data class Invalid(val errorMessages: List<String>) : FormControlStatus()

    object Valid : FormControlStatus()
}

fun FormControlStatus.isEnabled(): Boolean = this !is FormControlStatus.Disabled

fun FormControlStatus.isDisabled(): Boolean = this is FormControlStatus.Disabled

fun FormControlStatus.isInvalid(): Boolean = this is FormControlStatus.Invalid

fun FormControlStatus.isValid(): Boolean = this is FormControlStatus.Valid
