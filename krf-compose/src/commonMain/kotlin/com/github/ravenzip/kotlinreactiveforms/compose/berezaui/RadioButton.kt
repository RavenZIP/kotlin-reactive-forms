package com.github.ravenzip.kotlinreactiveforms.compose.berezaui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.radio.RadioButtonWithText
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
import com.github.ravenzip.kotlinreactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Composable
fun RadioButtonWithText(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    RadioButtonWithText(
        selected = state.value,
        enabled = state.enabled,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        text = text,
        padding = padding,
        colors = colors,
        shape = shape,
    )
}

@Composable
fun <T, K : Any> RadioGroup(
    control: MutableFormControl<T, ValidationError>,
    source: List<T>,
    keySelector: (T) -> K,
    text: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    RadioGroup(
        source = source,
        selectedItem = state.value,
        onSelectedItemChange = { value ->
            control.setValue(value)
            control.markAsDirty()
        },
        keySelector = keySelector,
        text = text,
        enabled = state.enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
