package com.github.ravenzip.reactiveforms.screen.components.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.OutlinedSingleLineTextField
import com.github.ravenzip.kotlinreactiveforms.compose.berezaui.SingleLineTextField
import com.github.ravenzip.kotlinreactiveforms.form.mutableFormControl
import com.github.ravenzip.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.reactiveforms.screen.components.shared.ComponentScreen

@Composable
fun SingleLineTextFieldScreen(navigationViewModel: RootNavigationViewModel) {
    val firstControl = remember { mutableFormControl("") }
    val secondControl = remember { mutableFormControl("") }

    ComponentScreen(
        title = "SingleLineTextField",
        description = "Текстовое поле для ввода однострочного текста.",
        goBack = { navigationViewModel.navigateBack() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SingleLineTextField(
                    control = firstControl,
                    label = { Text("SingleLineTextField") },
                )

                OutlinedSingleLineTextField(
                    control = secondControl,
                    label = { Text("OutlinedSingleLineTextField") },
                )
            }
        },
    )
}
