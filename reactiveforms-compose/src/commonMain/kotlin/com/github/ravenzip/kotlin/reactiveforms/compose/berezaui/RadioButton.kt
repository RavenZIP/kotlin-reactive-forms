package com.github.ravenzip.kotlin.reactiveforms.compose.berezaui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.radio.RadioButton
import com.github.ravenzip.berezaUI.core.components.radio.RadioGroup
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
    content: @Composable (RowScope.() -> Unit),
) {
    val state by control.collectAsComponentState()

    RadioButton(
        selected = state.value,
        enabled = state.enabled,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        padding = padding,
        colors = colors,
        shape = shape,
        content = content,
    )
}

@Composable
fun <T> RadioGroup(
    control: MutableFormControl<T, ValidationError>,
    source: SnapshotStateList<T>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    val state by control.collectAsComponentState()

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
        content = content,
    )
}
