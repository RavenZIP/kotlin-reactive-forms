package com.github.ravenzip.kotlinreactiveforms.extensions

import com.github.ravenzip.kotlinreactiveforms.data.FormControlSnapshot
import com.github.ravenzip.kotlinreactiveforms.data.ValueWithTypeChange
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

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
            errorMessages = errorMessages,
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
            errorMessages = errorMessages,
        )
    }
