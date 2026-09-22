package com.github.ravenzip.kotlinreactiveforms.data

import androidx.compose.runtime.Stable
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError

@Stable
data class FormControlState<TValue, out TError : ValidationError>(
    val value: TValue,
    val valueChangeType: ValueChangeType = ValueChangeType.Initialize,
    val status: FormControlStatus<TError>,
    val touched: Boolean = false,
    val dirty: Boolean = false,
)
