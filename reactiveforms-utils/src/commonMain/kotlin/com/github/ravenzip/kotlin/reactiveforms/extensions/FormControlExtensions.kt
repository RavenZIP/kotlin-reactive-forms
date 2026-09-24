package com.github.ravenzip.kotlin.reactiveforms.extensions

import com.github.ravenzip.kotlin.reactiveforms.data.FormControlSnapshot
import com.github.ravenzip.kotlin.reactiveforms.data.ValueWithTypeChange
import com.github.ravenzip.kotlin.reactiveforms.form.FormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError
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
