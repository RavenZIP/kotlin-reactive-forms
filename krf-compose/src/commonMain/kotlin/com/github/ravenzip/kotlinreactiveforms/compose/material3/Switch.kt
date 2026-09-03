package com.github.ravenzip.kotlinreactiveforms.compose.material3

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.github.ravenzip.kotlinreactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Composable
fun Switch(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    Switch(
        checked = state.value,
        onCheckedChange = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        colors = colors,
    )
}
