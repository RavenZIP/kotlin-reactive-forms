package com.github.ravenzip.kotlinreactiveforms.data

import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

data class FormControlSnapshot<TValue, TError : ValidationError>(
    val value: TValue,
    val valueChangeType: ValueChangeType,
    val hasValueChanges: Boolean,
    val status: FormControlStatus,
    val touched: Boolean,
    val dirty: Boolean,
    val valid: Boolean,
    val invalid: Boolean,
    val enabled: Boolean,
    val disabled: Boolean,
    val errors: List<TError>,
) {
    companion object {
        fun <TValue, TError : ValidationError> create(
            value: TValue,
            valueChangeType: ValueChangeType,
            status: FormControlStatus,
            touched: Boolean,
            dirty: Boolean,
            errors: List<TError>,
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
