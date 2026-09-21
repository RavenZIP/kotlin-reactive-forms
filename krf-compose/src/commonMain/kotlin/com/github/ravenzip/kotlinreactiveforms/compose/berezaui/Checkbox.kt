package com.github.ravenzip.kotlinreactiveforms.compose.berezaui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.checkbox.Checkbox
import com.github.ravenzip.berezaUI.core.components.checkbox.CheckboxGroup
import com.github.ravenzip.berezaUI.core.data.SelectionChange
import com.github.ravenzip.kotlinreactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.form.mergeValue
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val state by control.collectAsComponentState()

    Checkbox(
        selected = state.value,
        onClick = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        colors = colors,
        padding = padding,
        shape = shape,
        content = content,
    )
}

@Composable
fun <T> CheckboxGroup(
    control: MutableFormControl<SnapshotStateList<T>, ValidationError>,
    source: SnapshotStateList<T>,
    modifier: Modifier = Modifier,
    key: (T) -> Any? = { it },
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    padding: PaddingValues = PaddingValues(15.dp),
    shape: Shape = RoundedCornerShape(14.dp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
    content: @Composable RowScope.(T) -> Unit,
) {
    // TODO не должен ли быть value для списка все-таки List, а не SnapshotStateList?
    val state by control.collectAsComponentState()

    CheckboxGroup(
        source = source,
        selectedItems = state.value,
        onSelectionItemChange = { item, selectionChange ->
            when (selectionChange) {
                SelectionChange.Deselect -> {
                    val existingIndex = control.value.indexOfFirst { s -> s == item }
                    control.value.removeAt(existingIndex)
                }
                else -> control.value.add(item)
            }
            // TODO скорее всего сделать также, как в AppModule (выше)
            control.mergeValue(item, key)
            control.markAsTouched()
        },
        key = key,
        modifier = modifier,
        enabled = state.enabled,
        contentPadding = contentPadding,
        padding = padding,
        shape = shape,
        colors = colors,
        content = content,
    )
}
