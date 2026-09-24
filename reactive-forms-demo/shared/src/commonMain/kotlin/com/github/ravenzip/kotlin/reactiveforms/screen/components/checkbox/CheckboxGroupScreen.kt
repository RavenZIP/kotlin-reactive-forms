package com.github.ravenzip.kotlin.reactiveforms.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.berezaui.CheckboxGroup
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun CheckboxGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { mutableStateListOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(mutableStateListOf<String>()) }

    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            CheckboxGroup(
                control = firstControl,
                source = source,
                content = { x -> Text(x) },
            )
        },
    )
}
