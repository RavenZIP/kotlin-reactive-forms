package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.ChipOverflow
import com.github.ravenzip.bereza.core.components.textfield.select.MultiSelect
import com.github.ravenzip.bereza.core.components.textfield.select.OutlinedMultiSelect
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun <T> MultiSelect(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState(errorMessageProvider)

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    MultiSelect(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO
        },
        onSelect = { item ->
            // TODO
        },
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedMultiSelect(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.outlinedColors(),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState(errorMessageProvider)

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    OutlinedMultiSelect(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO
        },
        onSelect = { item ->
            // TODO
        },
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
