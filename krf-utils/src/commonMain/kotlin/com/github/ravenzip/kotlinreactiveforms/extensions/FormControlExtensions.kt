package com.github.ravenzip.kotlinreactiveforms.extensions

import com.github.ravenzip.kotlinreactiveforms.data.FormControlSnapshot
import com.github.ravenzip.kotlinreactiveforms.data.ValueWithTypeChange
import com.github.ravenzip.kotlinreactiveforms.data.extractErrors
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val <TValue, TError : ValidationError> FormControl<TValue, TError>.errorMessages: List<String>
    get() = status.extractErrors().map { error -> error.message }

val <TValue, TError : ValidationError> FormControl<TValue, TError>.firstErrorMessage: String
    get() = status.extractErrors().firstOrNull()?.message ?: ""

val <TValue, TError : ValidationError> FormControl<TValue, TError>.lastErrorMessage: String
    get() = status.extractErrors().lastOrNull()?.message ?: ""

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.errorMessagesChanges():
    Flow<List<String>> = stateChanges.map { state ->
    state.status.extractErrors().map { error -> error.message }
}

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.firstErrorMessageChanges():
    Flow<String> = stateChanges.map { state ->
    state.status.extractErrors().firstOrNull()?.message ?: ""
}

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.lastErrorMessageChanges():
    Flow<String> = stateChanges.map { state ->
    state.status.extractErrors().lastOrNull()?.message ?: ""
}

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
