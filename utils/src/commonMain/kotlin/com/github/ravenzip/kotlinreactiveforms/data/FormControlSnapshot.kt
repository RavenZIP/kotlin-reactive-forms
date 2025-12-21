package com.github.ravenzip.kotlinreactiveforms.data

import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

data class FormControlSnapshot<T>(
    val value: T,
    val valueChangeType: ValueChangeType,
    val hasValueChanges: Boolean,
    val status: FormControlStatus,
    val touched: Boolean,
    val dirty: Boolean,
    val valid: Boolean,
    val invalid: Boolean,
    val enabled: Boolean,
    val disabled: Boolean,
    val errors: List<ValidationError>,
) {
    companion object {
        fun <T> create(
            value: T,
            valueChangeType: ValueChangeType,
            status: FormControlStatus,
            touched: Boolean,
            dirty: Boolean,
            errors: List<ValidationError>,
        ) =
            FormControlSnapshot(
                value = value,
                valueChangeType = valueChangeType,
                hasValueChanges = !valueChangeType.isInitialize(),
                status = status,
                touched = touched,
                dirty = dirty,
                valid = status.isValid(),
                invalid = status.isInvalid(),
                enabled = status.isEnabled(),
                disabled = status.isDisabled(),
                errors = errors,
            )
    }
}
