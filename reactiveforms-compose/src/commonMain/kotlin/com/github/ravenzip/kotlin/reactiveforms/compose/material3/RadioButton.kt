package com.github.ravenzip.kotlin.reactiveforms.compose.material3

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun RadioButton(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    RadioButton(
        selected = state.value,
        onClick = {
            control.setValue(!state.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        colors = colors,
    )
}
