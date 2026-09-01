package com.github.ravenzip.kotlinreactiveforms.compose.berezaUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.switch.SwitchGroup
import com.github.ravenzip.berezaUI.core.components.switch.SwitchWithText
import com.github.ravenzip.kotlinreactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Composable
fun SwitchWithText(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    padding: PaddingValues = PaddingValues(15.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
    shape: Shape = RoundedCornerShape(14.dp),
) {
    val state by control.collectAsComponentState()

    SwitchWithText(
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
fun <T, K : Any> SwitchGroup(
    control: MutableFormControl<List<T>, ValidationError>,
    source: List<T>,
    keySelector: (T) -> K,
    modifier: Modifier = Modifier,
    text: @Composable (T) -> Unit,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    SwitchGroup(
        source = source,
        selectedItems = state.value,
        onSelectedItemChange = { selectedItem ->
            val selectedItemKey = keySelector(selectedItem)
            val selected = state.value.any { item -> keySelector(item) == selectedItemKey }
            val newSelectedItems =
                if (selected) state.value.filterNot { item -> keySelector(item) == selectedItemKey }
                else state.value + selectedItem

            control.setValue(newSelectedItems)
            control.markAsTouched()
        },
        keySelector = keySelector,
        modifier = modifier,
        text = text,
        enabled = state.enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
    )
}
