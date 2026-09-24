package com.github.ravenzip.kotlin.reactiveforms.data

import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

data class FormControlSnapshot<TValue, TError : ValidationError>(
    val value: TValue,
    val valueChangeType: ValueChangeType,
    val hasValueChanges: Boolean,
    val status: FormControlStatus<TError>,
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
            status: FormControlStatus<TError>,
            touched: Boolean,
            dirty: Boolean,
        ) =
            FormControlSnapshot(
                value = value,
                valueChangeType = valueChangeType,
                hasValueChanges = !valueChangeType.isInitialize(),
                status = status,
                touched = touched,
                dirty = dirty,
                valid = status.valid,
                invalid = status.invalid,
                enabled = status.enabled,
                disabled = status.disabled,
                errors = status.extractErrors(),
            )
    }
}
