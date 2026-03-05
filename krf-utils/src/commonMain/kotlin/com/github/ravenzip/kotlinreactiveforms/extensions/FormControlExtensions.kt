package com.github.ravenzip.kotlinreactiveforms.extensions

import com.github.ravenzip.kotlinreactiveforms.data.FormControlSnapshot
import com.github.ravenzip.kotlinreactiveforms.data.ValueWithTypeChange
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

val <TValue, TError : ValidationError> FormControl<TValue, TError>.errorMessages: List<String>
    get() = errors.map { error -> error.message }

val <TValue, TError : ValidationError> FormControl<TValue, TError>.firstErrorMessage: String
    get() = errors.firstOrNull()?.message ?: ""

val <TValue, TError : ValidationError> FormControl<TValue, TError>.lastErrorMessage: String
    get() = errors.lastOrNull()?.message ?: ""

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.errorMessagesChanges():
    Flow<List<String>> =
    errorsChanges.map { errorsChanges -> errorsChanges.map { error -> error.message } }

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.firstErrorMessageChanges():
    Flow<String> = errorsChanges.map { errorsChanges -> errorsChanges.firstOrNull()?.message ?: "" }

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.lastErrorMessageChanges():
    Flow<String> = errorsChanges.map { errorsChanges -> errorsChanges.lastOrNull()?.message ?: "" }

val <T> FormControl<T, ValidationError>.currentValueWithTypeChange: ValueWithTypeChange<T>
    get() = ValueWithTypeChange(value, valueChangeType)

fun <T> FormControl<T, ValidationError>.valueWithTypeChange(): Flow<ValueWithTypeChange<T>> =
    valueChanges.combine(valueChangeTypeChanges) { value, typeChange ->
        ValueWithTypeChange(value, typeChange)
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
            errors = errors,
        )

fun <TValue, TError : ValidationError> FormControl<TValue, TError>.snapshotChanges():
    Flow<FormControlSnapshot<TValue, TError>> =
    combine(valueChanges, valueChangeTypeChanges, statusChanges, touchedChanges, dirtyChanges) {
        value,
        typeChange,
        status,
        touched,
        dirty ->
        FormControlSnapshot.create(
            value = value,
            valueChangeType = typeChange,
            status = status,
            touched = touched,
            dirty = dirty,
            errors = errors,
        )
    }
