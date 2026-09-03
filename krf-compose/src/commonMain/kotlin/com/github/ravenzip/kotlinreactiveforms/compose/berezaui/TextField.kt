package com.github.ravenzip.kotlinreactiveforms.compose.berezaui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicOutlinedTextField
import com.github.ravenzip.berezaUI.core.components.textfield.basic.BasicTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.AutocompleteTextField
import com.github.ravenzip.berezaUI.core.components.textfield.dropdown.OutlinedAutocompleteTextField
import com.github.ravenzip.berezaUI.core.data.*
import com.github.ravenzip.kotlinreactiveforms.compose.shared.collectAsComponentState
import com.github.ravenzip.kotlinreactiveforms.form.MutableFormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Composable
fun SingleLineTextField(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit = {},
    isReadonly: Boolean = false,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    BasicTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun OutlinedSingleLineTextField(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit = {},
    isReadonly: Boolean = false,
    maxLength: Int? = null,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    BasicOutlinedTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        singleLine = true,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun MultiLineTextField(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    isReadonly: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    BasicTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
    )
}

@Composable
fun OutlinedMultiLineTextField(
    control: MutableFormControl<String, ValidationError>,
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit = {},
    isReadonly: Boolean = false,
    maxLength: Int? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    label: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    showTextLengthCounter: Boolean = false,
    showTextLengthCounterIfZero: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(14.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    BasicOutlinedTextField(
        value = state.value,
        onValueChange = { newValue ->
            control.setValue(newValue)
            control.markAsDirty()
        },
        modifier = modifier,
        enabled = state.enabled,
        readonly = isReadonly,
        mayHaveAnError = control.hasValidators,
        errorState = state.errorState,
        onFocusChange = onFocusChange,
        onTouchedChange = { control.markAsTouched() },
        maxLength = maxLength,
        maxLines = maxLines,
        minLines = minLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        showTextLengthCounter = showTextLengthCounter,
        showTextLengthCounterIfZero = showTextLengthCounterIfZero,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
    )
}

@Composable
@ExperimentalMaterial3Api
fun <TValue> AutocompleteTextField(
    control: MutableFormControl<TValue, ValidationError>,
    sourceState: SourceState<TValue>,
    clearValue: TValue,
    modifier: Modifier = Modifier,
    itemToString: (TValue) -> String,
    keySelector: ((TValue) -> Any)? = null,
    onTextChange: (String) -> Unit,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = true,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (TValue) -> Unit,
    dropDownMenuEmptyContent: @Composable () -> Unit,
    dropDownMenuLoadingContent: @Composable () -> Unit = dropDownMenuEmptyContent,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = DropDownTextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    AutocompleteTextField(
        sourceState = sourceState,
        selected = state.value,
        onSelectItem = { newSelected ->
            control.setValue(newSelected)
            control.markAsDirty()
        },
        onClearSelected = { control.setValue(clearValue) },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = state.errorState,
        enabled = state.enabled,
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        collapseAfterSelect = collapseAfterSelect,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuEmptyContent = dropDownMenuEmptyContent,
        dropDownMenuLoadingContent = dropDownMenuLoadingContent,
        shape = shape,
        colors = colors,
    )
}

@Composable
@ExperimentalMaterial3Api
fun <TValue> OutlinedAutocompleteTextField(
    control: MutableFormControl<TValue, ValidationError>,
    sourceState: SourceState<TValue>,
    clearValue: TValue,
    modifier: Modifier = Modifier,
    itemToString: (TValue) -> String,
    keySelector: ((TValue) -> Any)? = null,
    onTextChange: (String) -> Unit,
    readOnly: Boolean = false,
    onExpandedChange: (DropDownExpandEvent) -> Unit = {},
    collapseAfterSelect: Boolean = true,
    onFocusChange: (FocusState) -> Unit = {},
    onTouchChange: () -> Unit = {},
    textFieldLabel: (@Composable () -> Unit)? = null,
    textFieldLeadingIcon: (@Composable () -> Unit)? = null,
    textFieldTrailingIcon: (@Composable () -> Unit)? = null,
    dropDownMenuItemContent: @Composable (TValue) -> Unit,
    dropDownMenuEmptyContent: @Composable () -> Unit,
    dropDownMenuLoadingContent: @Composable () -> Unit = dropDownMenuEmptyContent,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: DropDownTextFieldColors = OutlinedDropDownTextFieldDefaults.colors(),
) {
    val state by control.collectAsComponentState()

    OutlinedAutocompleteTextField(
        sourceState = sourceState,
        selected = state.value,
        onSelectItem = { newSelectedItem ->
            control.setValue(newSelectedItem)
            control.markAsDirty()
        },
        onClearSelected = { control.setValue(clearValue) },
        itemToString = itemToString,
        keySelector = keySelector,
        onTextChange = { newText ->
            control.markAsDirty()
            onTextChange(newText)
        },
        modifier = modifier,
        errorState = state.errorState,
        enabled = state.enabled,
        readOnly = readOnly,
        onExpandedChange = onExpandedChange,
        collapseAfterSelect = collapseAfterSelect,
        onFocusChange = onFocusChange,
        onTouchChange = onTouchChange,
        textFieldLabel = textFieldLabel,
        textFieldLeadingIcon = textFieldLeadingIcon,
        textFieldTrailingIcon = textFieldTrailingIcon,
        dropDownMenuItemContent = dropDownMenuItemContent,
        dropDownMenuEmptyContent = dropDownMenuEmptyContent,
        dropDownMenuLoadingContent = dropDownMenuLoadingContent,
        shape = shape,
        colors = colors,
    )
}
