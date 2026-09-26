package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.textfield.select.OutlinedSelect
import com.github.ravenzip.bereza.core.components.textfield.select.Select
import com.github.ravenzip.bereza.core.data.DropDownTextFieldColors
import com.github.ravenzip.bereza.core.data.DropDownTextFieldDefaults
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

// TODO Надо как-то регулировать видимость крестика очистки. В UI библиотеке за это отвечает
// наличие коллбэка
@Composable
fun <T> Select(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
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

    Select(
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
        key = key,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun <T> OutlinedSelect(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    displayWith: (T) -> String,
    key: (T) -> Any? = { it },
    errorMessageProvider: ((ValidationError) -> String)? = null,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    clearIcon: @Composable (() -> Unit)? = null,
    dropDownIcon: @Composable ((expanded: Boolean) -> Unit)? = null,
    enabled: Boolean = true,
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

    OutlinedSelect(
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
        key = key,
        label = label,
        placeholder = placeholder,
        clearIcon = clearIcon,
        dropDownIcon = dropDownIcon,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
    )
}
