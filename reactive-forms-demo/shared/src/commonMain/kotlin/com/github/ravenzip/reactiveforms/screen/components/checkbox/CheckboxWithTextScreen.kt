package com.github.ravenzip.reactiveforms.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.Checkbox
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun CheckboxWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "CheckboxWithText",
        description = "Переключатель, аналогичный Checkbox из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Checkbox(
                control = firstControl,
                content = { Text("С текстом") },
            )
        },
    )
}
