package com.github.ravenzip.kotlinreactiveforms

import com.github.ravenzip.kotlinreactiveforms.data.ValueChangeType
import com.github.ravenzip.kotlinreactiveforms.data.isInvalid
import com.github.ravenzip.kotlinreactiveforms.data.isValid
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.Validator
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FormControlTest {
    @Test
    fun `initial disabled is false when initiallyDisabled is false`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)

        assertFalse(control.disabled)
    }

    @Test
    fun `initial disabled is true when initiallyDisabled is true`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 0,
                initiallyDisabled = true,
                coroutineScope = backgroundScope,
            )

        assertTrue(control.disabled)
    }

    @Test
    fun `initial dirty is false`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)

        assertFalse(control.dirty)
    }

    @Test
    fun `initial touched is false`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)

        assertFalse(control.touched)
    }

    @Test
    fun `initial status is valid without validators`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)

        assertTrue(control.status.isValid())
    }

    @Test
    fun `initial status is invalid with validators and wrong value`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 0,
                validators = listOf(Validator.min(1)),
                coroutineScope = backgroundScope,
            )

        assertTrue(control.status.isInvalid())
    }

    @Test
    fun `initial status is valid with validators and correct value`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 2,
                validators = listOf(Validator.min(1)),
                coroutineScope = backgroundScope,
            )

        assertTrue(control.status.isValid())
    }

    @Test
    fun `initial errorMessages is empty without validators`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)

        assertTrue(control.errorMessages.isEmpty())
    }

    @Test
    fun `initial errorMessages is not empty with validators and wrong value`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 0,
                validators = listOf(Validator.min(1)),
                coroutineScope = backgroundScope,
            )

        assertTrue(control.errorMessages.isNotEmpty())
    }

    @Test
    fun `initial errorMessages is empty with validators and correct value`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 2,
                validators = listOf(Validator.min(1)),
                coroutineScope = backgroundScope,
            )

        assertTrue(control.errorMessages.isEmpty())
    }

    @Test
    fun `initial value equal initialValue`() = runTest {
        val initialValue = 0
        val control =
            mutableFormControl(initialValue = initialValue, coroutineScope = backgroundScope)

        assertEquals(control.value, initialValue)
    }

    @Test
    fun `initial typeChange equal Initialize`() = runTest {
        val control = mutableFormControl(initialValue = 0, coroutineScope = backgroundScope)
        assertEquals(control.typeChange, ValueChangeType.Initialize)
    }
}
