package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.switch.Switch
import com.github.ravenzip.bereza.core.components.switch.SwitchGroup
import com.github.ravenzip.bereza.core.data.SelectionChange
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun Switch(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState()

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    Switch(
        checked = state.value,
        onCheckedChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        enabled = state.enabled,
        modifier = modifier,
        padding = padding,
        colors = colors,
        shape = shape,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun <T> SwitchGroup(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.(T) -> Unit,
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState()

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    SwitchGroup(
        source = source,
        selectedItems = state.value,
        onSelectionItemChange = { item, selectionChange ->
            when (selectionChange) {
                SelectionChange.Deselect -> {
                    val itemKey = key(item)
                    val existingIndex = state.value.indexOfFirst { s -> key(s) == itemKey }
                    val newSelected = state.value.toMutableList().apply { removeAt(existingIndex) }
                    control.setValue(newSelected)
                }
                else -> control.setValue(state.value + item)
            }
            control.markAsDirty()
        },
        key = key,
        modifier = modifier,
        enabled = state.enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
        interactionSource = interactionSource,
        content = content,
    )
}
