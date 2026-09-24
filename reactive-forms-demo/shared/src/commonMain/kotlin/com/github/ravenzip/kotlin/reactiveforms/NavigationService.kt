package com.github.ravenzip.kotlin.reactiveforms

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.github.ravenzip.kotlin.reactiveforms.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    val componentScreens =
        mutableStateListOf(
            Screen.AutocompleteTextField,
            Screen.Switch,
            Screen.Checkbox,
            Screen.RadioButton,
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
