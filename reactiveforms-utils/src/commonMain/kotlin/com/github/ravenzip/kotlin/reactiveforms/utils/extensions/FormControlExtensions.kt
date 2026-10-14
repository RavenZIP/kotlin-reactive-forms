package com.github.ravenzip.kotlin.reactiveforms.utils.extensions

import com.github.ravenzip.kotlin.reactiveforms.core.form.FormControl
import com.github.ravenzip.kotlin.reactiveforms.core.validation.ValidationError
import com.github.ravenzip.kotlin.reactiveforms.utils.data.FormControlSnapshot
import com.github.ravenzip.kotlin.reactiveforms.utils.data.ValueWithTypeChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val <T> FormControl<T, ValidationError>.currentValueWithTypeChange: ValueWithTypeChange<T>
    get() = ValueWithTypeChange(value, valueChangeType)

fun <T> FormControl<T, ValidationError>.valueWithTypeChange(): Flow<ValueWithTypeChange<T>> =
    stateChanges.map { state ->
        ValueWithTypeChange(state.value, state.valueChangeType)
    }

val <TValue, TError : ValidationError> FormControl<TValue, TError>.snapshot:
    FormControlSnapshot<TValue, TError>
    get() =
        FormControlSnapshot.create(
            value = value,
            valueChangeType = valueChangeType,
            status = status,
            touched = touched,
            dirty = dirty,
        )

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.snapshotChanges():
    Flow<FormControlSnapshot<TValue, TError>> = stateChanges.map { state ->
    FormControlSnapshot.create(
        value = state.value,
        valueChangeType = state.valueChangeType,
        status = state.status,
        touched = state.touched,
        dirty = state.dirty,
    )
}
