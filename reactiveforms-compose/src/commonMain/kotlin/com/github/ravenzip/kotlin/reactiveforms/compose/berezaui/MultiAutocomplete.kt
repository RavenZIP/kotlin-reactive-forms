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
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.MultiAutocomplete
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.OutlinedMultiAutocomplete
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> MultiAutocomplete(
    control: MutableFormControl<List<T>, ValidationError>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    search: (String) -> Flow<List<T>>,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable (() -> Unit),
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

    MultiAutocomplete(
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO подумать как искать элементы и добавлять\кикать
            control.setValue(listOf())
            control.markAsDirty()
        },
        onSelect = { item ->
            control.setValue(listOf())
            control.markAsDirty()
        },
        search = search,
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedMultiAutocomplete(
    control: MutableFormControl<List<T>, ValidationError>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    search: (String) -> Flow<List<T>>,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable (() -> Unit),
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

    OutlinedMultiAutocomplete(
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO подумать как искать элементы и добавлять\кикать
            control.setValue(listOf())
            control.markAsDirty()
        },
        onSelect = { item ->
            control.setValue(listOf())
            control.markAsDirty()
        },
        search = search,
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> MultiAutocomplete(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    search: (T, String) -> Boolean,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable (() -> Unit),
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

    MultiAutocomplete(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO подумать как искать элементы и добавлять\кикать
            control.setValue(listOf())
            control.markAsDirty()
        },
        onSelect = { item ->
            control.setValue(listOf())
            control.markAsDirty()
        },
        search = search,
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedMultiAutocomplete(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    search: (T, String) -> Boolean,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    chipOverflow: ChipOverflow = ChipOverflow.Wrap,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    itemContent: @Composable (T) -> Unit,
    emptyContent: @Composable (() -> Unit),
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

    OutlinedMultiAutocomplete(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onRemoveChip = { item ->
            // TODO подумать как искать элементы и добавлять\кикать
            control.setValue(listOf())
            control.markAsDirty()
        },
        onSelect = { item ->
            control.setValue(listOf())
            control.markAsDirty()
        },
        search = search,
        errorState = state.errorState,
        key = key,
        enabled = state.enabled,
        chipOverflow = chipOverflow,
        label = label,
        placeholder = placeholder,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
