package com.github.ravenzip.kotlin.reactiveforms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.github.ravenzip.kotlin.reactiveforms.data.Screen
import com.github.ravenzip.kotlin.reactiveforms.screen.HomeScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.checkbox.CheckboxGroupScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.checkbox.CheckboxScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.radiobutton.RadioButtonScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.radiobutton.RadioGroupScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.switch.SwitchGroupScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.switch.SwitchScreen
import com.github.ravenzip.kotlin.reactiveforms.screen.components.textfield.AutocompleteTextFieldScreen

@Composable
fun RootNavigation(
    navigationViewModel: RootNavigationViewModel = remember { RootNavigationViewModel() }
) {
    //    val backStack = rememberNavBackStack(createRouteNavigationConfig(), Screen.Home)

    NavDisplay(
        backStack = navigationViewModel.backStack,
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider = { key ->
            NavEntry(key) {
                when (key) {
                    is Screen.Home -> {
                        HomeScreen(navigationViewModel)
                    }

                    // Текстовые поля
                    is Screen.AutocompleteTextField -> {
                        AutocompleteTextFieldScreen(navigationViewModel)
                    }

                    // Чекбоксы
                    is Screen.Checkbox -> {
                        CheckboxScreen(navigationViewModel)
                    }

                    is Screen.CheckboxGroup -> {
                        CheckboxGroupScreen(navigationViewModel)
                    }

                    // Свичи
                    is Screen.Switch -> {
                        SwitchScreen(navigationViewModel)
                    }

                    is Screen.SwitchGroup -> {
                        SwitchGroupScreen(navigationViewModel)
                    }

                    // Радиокнопки
                    is Screen.RadioButton -> {
                        RadioButtonScreen(navigationViewModel)
                    }

                    is Screen.RadioGroup -> {
                        RadioGroupScreen(navigationViewModel)
                    }
                }
            }
        },
    )
}
