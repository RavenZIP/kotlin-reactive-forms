package com.github.ravenzip.kotlin.reactiveforms.screen.components.radiobutton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.compose.berezaui.RadioGroup
import com.github.ravenzip.kotlin.reactiveforms.form.mutableFormControl
import com.github.ravenzip.kotlin.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun RadioGroupScreen(navigationViewModel: RootNavigationViewModel) {
    val source = remember { mutableStateListOf("Русский", "Английский", "Прочее") }
    val firstControl = remember { mutableFormControl(source.first()) }

    ComponentScreen(
        title = "RadioGroup",
        description = "Группа из нескольких RadioButton с текстовой подписью",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            RadioGroup(
                control = firstControl,
                source = source,
                key = { x -> x },
                content = { x -> Text(x) },
            )
        },
    )
}
