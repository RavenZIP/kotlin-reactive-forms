package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.Autocomplete
import com.github.ravenzip.bereza.core.components.textfield.autocomplete.OutlinedAutocomplete
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> Autocomplete(
    control: MutableFormControl<T, ValidationError>,
    displayWith: (T) -> String,
    search: (String) -> Flow<List<T>>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: (@Composable (T) -> Unit)? = null,
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

    Autocomplete(
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onSelect = { item ->
            control.setValue(item)
            control.markAsDirty()
        },
        onClear = { control.reset() },
        errorState = state.errorState,
        search = search,
        key = key,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedAutocomplete(
    control: MutableFormControl<T, ValidationError>,
    displayWith: (T) -> String,
    search: (String) -> Flow<List<T>>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: (@Composable (T) -> Unit)? = null,
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

    OutlinedAutocomplete(
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onSelect = { item ->
            control.setValue(item)
            control.markAsDirty()
        },
        onClear = { control.reset() },
        errorState = state.errorState,
        search = search,
        key = key,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

// TODO сделать AutocompleteMenuItem публичным в BerezaUI, чтобы можно было его здесь использовать
// как дефолт
// Либо переделать сигнатуру на nullable
@Composable
fun <T> Autocomplete(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    displayWith: (T) -> String,
    search: (T, String) -> Boolean,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: (@Composable (T) -> Unit)? = null,
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

    Autocomplete(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onSelect = { item ->
            control.setValue(item)
            control.markAsDirty()
        },
        onClear = { control.reset() },
        errorState = state.errorState,
        search = search,
        key = key,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedAutocomplete(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    displayWith: (T) -> String,
    search: (T, String) -> Boolean,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    itemContent: (@Composable (T) -> Unit)? = null,
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

    OutlinedAutocomplete(
        source = source,
        modifier = modifier,
        selected = state.value,
        displayWith = displayWith,
        onSelect = { item ->
            control.setValue(item)
            control.markAsDirty()
        },
        onClear = { control.reset() },
        errorState = state.errorState,
        search = search,
        key = key,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        itemContent = {},
        emptyContent = emptyContent,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
