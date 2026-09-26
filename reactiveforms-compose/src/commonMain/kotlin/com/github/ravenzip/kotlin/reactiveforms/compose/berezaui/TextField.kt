package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.OutlinedTextFieldWithSupportingRow
import com.github.ravenzip.bereza.core.components.textfield.TextFieldWithSupportingRow
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun TextFieldWithSupportingRow(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    errorMessageProvider: ((ValidationError) -> String)? = null,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState(errorMessageProvider)

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    TextFieldWithSupportingRow(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = readonly,
        reserveSupportingContentSpace = reserveSupportingContentSpace,
        errorState = state.errorState,
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun OutlinedTextFieldWithSupportingRow(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    errorMessageProvider: ((ValidationError) -> String)? = null,
    readonly: Boolean = false,
    reserveSupportingContentSpace: Boolean = false,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState(errorMessageProvider)

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    OutlinedTextFieldWithSupportingRow(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = readonly,
        reserveSupportingContentSpace = reserveSupportingContentSpace,
        errorState = state.errorState,
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
