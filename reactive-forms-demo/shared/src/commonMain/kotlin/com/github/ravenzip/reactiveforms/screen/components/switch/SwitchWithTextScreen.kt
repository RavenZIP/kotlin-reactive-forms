package com.github.ravenzip.reactiveforms.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.Switch
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun SwitchWithTextScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }
    val secondControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "SwitchWithText",
        description = "Переключатель, аналогичный Switch из Material 3, но с текстовой подписью.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Switch(
                control = firstControl,
                content = { Text("С текстом") },
            )
        },
    )
}
