package com.github.ravenzip.reactiveforms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.github.ravenzip.reactiveforms.data.Screen
import com.github.ravenzip.reactiveforms.screen.HomeScreen
import com.github.ravenzip.reactiveforms.screen.components.checkbox.CheckboxGroupScreen
import com.github.ravenzip.reactiveforms.screen.components.checkbox.CheckboxWithTextScreen
import com.github.ravenzip.reactiveforms.screen.components.radiobutton.RadioButtonWithTextScreen
import com.github.ravenzip.reactiveforms.screen.components.radiobutton.RadioGroupScreen
import com.github.ravenzip.reactiveforms.screen.components.switch.SwitchGroupScreen
import com.github.ravenzip.reactiveforms.screen.components.switch.SwitchWithTextScreen
import com.github.ravenzip.reactiveforms.screen.components.textfield.AutocompleteTextFieldScreen
import com.github.ravenzip.reactiveforms.screen.components.textfield.DropDownTextFieldScreen
import com.github.ravenzip.reactiveforms.screen.components.textfield.MultiLineTextFieldScreen
import com.github.ravenzip.reactiveforms.screen.components.textfield.SingleLineTextFieldScreen

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
                    is Screen.SingleLineTextField -> {
                        SingleLineTextFieldScreen(navigationViewModel)
                    }

                    is Screen.MultiLineTextField -> {
                        MultiLineTextFieldScreen(navigationViewModel)
                    }

                    is Screen.DropDownTextField -> {
                        DropDownTextFieldScreen(navigationViewModel)
                    }

                    is Screen.AutocompleteTextField -> {
                        AutocompleteTextFieldScreen(navigationViewModel)
                    }

                    // Чекбоксы
                    is Screen.CheckboxWithText -> {
                        CheckboxWithTextScreen(navigationViewModel)
                    }

                    is Screen.CheckboxGroup -> {
                        CheckboxGroupScreen(navigationViewModel)
                    }

                    // Свичи
                    is Screen.SwitchWithText -> {
                        SwitchWithTextScreen(navigationViewModel)
                    }

                    is Screen.SwitchGroup -> {
                        SwitchGroupScreen(navigationViewModel)
                    }

                    // Радиокнопки
                    is Screen.RadioButtonWithText -> {
                        RadioButtonWithTextScreen(navigationViewModel)
                    }

                    is Screen.RadioGroup -> {
                        RadioGroupScreen(navigationViewModel)
                    }
                }
            }
        },
    )
}
