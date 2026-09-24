package com.github.ravenzip.kotlin.reactiveforms

import com.github.ravenzip.kotlin.reactiveforms.data.*
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.RangeValidator
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

// TODO разобраться с тестами. Возможно, стоит их пересмотреть, но только после окончательной правки
// архитектуры
class FormControlTest {
    @Test
    fun `initial disabled is false when initiallyDisabled is false`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertFalse(control.status.disabled)
    }

    @Test
    fun `initial disabled is true when initiallyDisabled is true`() = runTest {
        val control = mutableFormControl(initialValue = 0, disabled = true)

        assertTrue(control.status.disabled)
    }

    @Test
    fun `initial dirty is false`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertFalse(control.dirty)
    }

    @Test
    fun `initial touched is false`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertFalse(control.touched)
    }

    @Test
    fun `initial status is valid without validators`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertTrue(control.status.valid)
    }

    @Test
    fun `initial status is invalid with validators and wrong value`() = runTest {
        val control =
            mutableFormControl(initialValue = 0, validators = listOf(RangeValidator.min(1)))

        assertTrue(control.status.invalid)
    }

    @Test
    fun `initial status is valid with validators and correct value`() = runTest {
        val control =
            mutableFormControl(initialValue = 2, validators = listOf(RangeValidator.min(1)))

        assertTrue(control.status.valid)
    }

    @Test
    fun `initial errors is empty without validators`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertTrue(control.status.extractErrors().isEmpty())
    }

    @Test
    fun `initial errors is not empty with validators and wrong value`() = runTest {
        val control =
            mutableFormControl(initialValue = 0, validators = listOf(RangeValidator.min(1)))

        assertTrue(control.status.extractErrors().isNotEmpty())
    }

    @Test
    fun `initial errors is empty with validators and correct value`() = runTest {
        val control =
            mutableFormControl(initialValue = 2, validators = listOf(RangeValidator.min(1)))

        assertTrue(control.status.extractErrors().isEmpty())
    }

    @Test
    fun `initial value equal initialValue`() = runTest {
        val initialValue = 0
        val control = mutableFormControl(initialValue = initialValue)

        assertEquals(initialValue, control.value)
    }

    @Test
    fun `initial valueChangeType equal Initialize`() = runTest {
        val control = mutableFormControl(initialValue = 0)

        assertEquals(ValueChangeType.Initialize, control.valueChangeType)
    }

    @Test
    fun `control enabled after call enable()`() = runTest {
        val control = mutableFormControl(initialValue = 0, disabled = true)
        control.enable()

        assertFalse(control.status.disabled)
    }

    @Test
    fun `control disabled after call disable()`() = runTest {
        val control = mutableFormControl(initialValue = 0)
        control.disable()

        assertTrue(control.status.disabled)
    }

    @Test
    fun `control status is disabled after call disable()`() = runTest {
        val control = mutableFormControl(initialValue = 0)
        control.disable()

        assertTrue(control.status.disabled)
    }

    @Test
    fun `control status is invalid after call enable with wrong value()`() = runTest {
        val control =
            mutableFormControl(
                initialValue = 0,
                disabled = true,
                validators = listOf(RangeValidator.min(1)),
            )
        control.enable()

        assertTrue(control.status.invalid)
    }

    @Test
    fun `control value changed to newValue after setValue()`() = runTest {
        val expectedValue = 1
        val control = mutableFormControl(initialValue = 0)
        control.setValue(expectedValue)

        assertEquals(expectedValue, control.value)
    }

    @Test
    fun `control valueChangeType changed to Set after setValue()`() = runTest {
        val control = mutableFormControl(initialValue = 0)
        control.setValue(1)

        assertEquals(ValueChangeType.Set, control.valueChangeType)
    }

    @Test
    fun `control have errors after call setValue with invalid value`() = runTest {
        val control =
            mutableFormControl(initialValue = 10, validators = listOf(RangeValidator.min(1)))
        control.setValue(0)

        assertTrue(control.status.extractErrors().isNotEmpty())
    }

    @Test
    fun `control status changed to invalid after call setValue with invalid value`() = runTest {
        val control =
            mutableFormControl(initialValue = 10, validators = listOf(RangeValidator.min(1)))
        control.setValue(0)

        assertTrue(control.status.invalid)
    }
}
