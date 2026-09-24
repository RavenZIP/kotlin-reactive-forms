package com.github.ravenzip.kotlin.reactiveforms.screen.components.radiobutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.material3.RadioButton
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun RadioButtonScreen(navigationViewModel: RootNavigationViewModel) {
    val control = remember { mutableFormControl(false) }

    ComponentScreen(
        title = "RadioButton",
        description = "TODO",
        hasIntegrationWithReactiveForms = false,
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioButton(control = control)
        },
    )
}
