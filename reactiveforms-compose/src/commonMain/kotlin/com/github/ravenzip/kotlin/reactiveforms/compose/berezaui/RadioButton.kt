package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.bereza.core.components.radio.RadioButton
import com.github.ravenzip.bereza.core.components.radio.RadioGroup
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun RadioButton(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable (RowScope.() -> Unit),
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState()

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    RadioButton(
        selected = state.value,
        onClick = {
            control.setValue(!control.value)
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
fun <T> RadioGroup(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.(T) -> Unit,
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState()

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    RadioGroup(
        source = source,
        selectedItem = state.value,
        onSelectionItemChange = { value ->
            control.setValue(value)
            control.markAsDirty()
        },
        key = key,
        enabled = state.enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
        interactionSource = interactionSource,
        content = content,
    )
}
