package extensions

import data.FormControlSnapshot
import data.ValueWithTypeChange
import form.FormControl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun <T> FormControl<T>.valueWithTypeChange(): Flow<ValueWithTypeChange<T>> =
    valueChanges.combine(typeChanges) { value, typeChange ->
        ValueWithTypeChange(value, typeChange)
    }

val <T> FormControl<T>.snapshot
    get(): FormControlSnapshot<T> =
        FormControlSnapshot.create(
            value = value,
            typeChange = typeChange,
            status = status,
            touched = touched,
            dirty = dirty,
            errorMessages = errorMessages,
        )

fun <T> FormControl<T>.snapshotChanges(): Flow<FormControlSnapshot<T>> =
    combine(valueChanges, typeChanges, statusChanges, touchedChanges, dirtyChanges) {
        value,
        typeChange,
        status,
        touched,
        dirty ->
        FormControlSnapshot.create(
            value = value,
            typeChange = typeChange,
            status = status,
            touched = touched,
            dirty = dirty,
            errorMessages = errorMessages,
        )
    }
