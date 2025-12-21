package com.github.ravenzip.kotlinreactiveforms.extensions

import com.github.ravenzip.kotlinreactiveforms.data.FormControlSnapshot
import com.github.ravenzip.kotlinreactiveforms.data.ValueWithTypeChange
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

val <T> FormControl<T>.errorMessages: List<String>
    get() = errors.map { error -> error.message }

val <T> FormControl<T>.firstErrorMessage: String
    get() = errors.firstOrNull()?.message ?: ""

val <T> FormControl<T>.lastErrorMessage: String
    get() = errors.lastOrNull()?.message ?: ""

fun <T> FormControl<T>.errorMessagesChanges(): Flow<List<String>> =
    errorsChanges.map { errorsChanges -> errorsChanges.map { error -> error.message } }

fun <T> FormControl<T>.firstErrorMessageChanges(): Flow<String> =
    errorsChanges.map { errorsChanges -> errorsChanges.firstOrNull()?.message ?: "" }

fun <T> FormControl<T>.lastErrorMessageChanges(): Flow<String> =
    errorsChanges.map { errorsChanges -> errorsChanges.lastOrNull()?.message ?: "" }

val <T> FormControl<T>.currentValueWithTypeChange: ValueWithTypeChange<T>
    get() = ValueWithTypeChange(value, valueChangeType)

fun <T> FormControl<T>.valueWithTypeChange(): Flow<ValueWithTypeChange<T>> =
    valueChanges.combine(valueChangeTypeChanges) { value, typeChange ->
        ValueWithTypeChange(value, typeChange)
    }

val <T> FormControl<T>.snapshot: FormControlSnapshot<T>
    get() =
        FormControlSnapshot.create(
            value = value,
            valueChangeType = valueChangeType,
            status = status,
            touched = touched,
            dirty = dirty,
            errors = errors,
        )

fun <T> FormControl<T>.snapshotChanges(): Flow<FormControlSnapshot<T>> =
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
