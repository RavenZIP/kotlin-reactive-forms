package com.github.ravenzip.reactiveforms.screen.components.checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.CheckboxGroup
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun CheckboxGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(listOf<String>()) }

    ComponentScreen(
        title = "CheckboxGroup",
        description = "Группа из нескольких Checkbox с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            CheckboxGroup(
                control = firstControl,
                source = source,
                keySelector = { x -> x },
                content = { x -> Text(x) },
            )
        },
    )
}
