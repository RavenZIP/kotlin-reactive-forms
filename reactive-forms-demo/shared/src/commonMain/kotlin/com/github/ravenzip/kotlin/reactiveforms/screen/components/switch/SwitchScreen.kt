package com.github.ravenzip.kotlin.reactiveforms.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.berezaui.Switch
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun SwitchScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "Switch",
        description = "TODO",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Switch(
                control = firstControl,
                content = { Text("С текстом") },
            )
        },
    )
}
