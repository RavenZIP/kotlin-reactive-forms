package com.github.ravenzip.kotlin.reactiveforms.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.berezaui.Checkbox
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun CheckboxScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "Checkbox",
        description = "TODO",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Checkbox(
                control = firstControl,
                content = { Text("С текстом") },
            )
        },
    )
}
