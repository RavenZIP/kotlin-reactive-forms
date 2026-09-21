package com.github.ravenzip.reactiveforms.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.SwitchGroup
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun SwitchGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { listOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(listOf<String>()) }

    // TODO не хардкодить названия
    ComponentScreen(
        title = "SwitchGroup",
        description = "Группа из нескольких Switch с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SwitchGroup(
                control = firstControl,
                source = source,
                key = { x -> x },
                content = { x -> Text(x) },
            )
        },
    )
}
