package com.github.ravenzip.reactiveforms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.github.ravenzip.reactiveforms.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    val componentScreens =
        mutableStateListOf(
            Screen.SingleLineTextField,
            Screen.MultiLineTextField,
            Screen.DropDownTextField,
            Screen.AutocompleteTextField,
            Screen.SwitchWithText,
            Screen.CheckboxWithText,
            Screen.RadioButtonWithText,
            Screen.CheckboxGroup,
            Screen.RadioGroup,
            Screen.SwitchGroup,
        )

    fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    fun navigateBack() {
        backStack.remove(backStack.last())
    }
}
