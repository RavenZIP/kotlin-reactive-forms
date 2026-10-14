package com.github.ravenzip.kotlin.reactiveforms.compose.material3

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.FocusLostEffect
import com.github.ravenzip.kotlin.reactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlin.reactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.validation.ValidationError

@Composable
fun Checkbox(
    control: MutableFormControl<Boolean, ValidationError>,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    interactionSource: MutableInteractionSource? = null,
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val state by control.collectAsComponentState()

    FocusLostEffect(
        interactionSource = interactionSource,
        onFocusLost = { control.markAsTouched() },
    )

    Checkbox(
        checked = state.value,
        onCheckedChange = {
            control.setValue(!control.value)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        colors = colors,
        interactionSource = interactionSource,
    )
}
