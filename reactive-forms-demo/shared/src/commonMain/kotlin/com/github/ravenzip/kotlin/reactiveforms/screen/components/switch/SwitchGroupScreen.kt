package com.github.ravenzip.kotlin.reactiveforms.screen.components.switch

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.berezaui.SwitchGroup
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun SwitchGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { mutableStateListOf("Русский", "Английский", "Прочее") }
    val control = remember { mutableFormControl(mutableStateListOf<String>()) }

    // TODO не хардкодить названия
    ComponentScreen(
        title = "SwitchGroup",
        description = "Группа из нескольких Switch с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            SwitchGroup(
                control = control,
                source = source,
                key = { x -> x },
                content = { x -> Text(x) },
            )
        },
    )
}
