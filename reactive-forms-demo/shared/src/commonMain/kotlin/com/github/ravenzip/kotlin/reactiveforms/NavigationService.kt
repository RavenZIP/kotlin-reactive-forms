package com.github.ravenzip.kotlin.reactiveforms

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.github.ravenzip.kotlin.reactiveforms.data.Screen

class RootNavigationViewModel : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Home)

    // Если не указать тип явно, то при использовании items(navigationViewModel.componentScreens) {
    // item -> } тип определяется как Any и компиляция wasmJs\Js падает (android и jvm норм)
    val componentScreens: SnapshotStateList<Screen> =
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
