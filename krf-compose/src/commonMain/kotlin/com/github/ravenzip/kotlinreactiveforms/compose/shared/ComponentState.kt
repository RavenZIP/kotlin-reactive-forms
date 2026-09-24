package com.github.ravenzip.kotlinreactiveforms.compose.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.github.ravenzip.berezaUI.core.data.ComponentErrorState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlState
import com.github.ravenzip.kotlinreactiveforms.data.FormControlStatus
import com.github.ravenzip.kotlinreactiveforms.data.enabled
import com.github.ravenzip.kotlinreactiveforms.form.FormControl
import com.github.ravenzip.kotlinreactiveforms.validation.ValidationError
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Stable
data class ComponentState<T>(
    val value: T,
    val enabled: Boolean,
    val errorState: ComponentErrorState,
)

fun <T> computeComponentState(state: FormControlState<T, ValidationError>): ComponentState<T> {
    val errorState =
        when (val status = state.status) {
            FormControlStatus.Disabled,
            FormControlStatus.Valid -> {
                ComponentErrorState.Ok
            }

            is FormControlStatus.Invalid -> {
                if (state.dirty || state.touched) {
                    // Не упадем, потому что в случае статуса Invalid текст ошибки должен быть
                    // всегда
                    val errorMessage = status.errors.first().message
                    ComponentErrorState.Error(errorMessage)
                } else {
                    ComponentErrorState.Ok
                }
            }
        }

    return ComponentState(
        value = state.value,
        enabled = state.status.enabled,
        errorState = errorState,
    )
}

fun <TValue> FormControl<TValue, ValidationError>.computeComponentState(): ComponentState<TValue> =
    computeComponentState(FormControlState(value = value, status = status))

@Composable
fun <TValue> FormControl<TValue, ValidationError>.collectAsComponentState(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<ComponentState<TValue>> = collectAsComponentState(lifecycleOwner.lifecycle, minActiveState)

/**
 * Согласно документации, с недавнего времени Lifecycle появился и на остальных платформах, кроме
 * Android. Поэтому реализовал такой же механизм, как и в collectAsStateWithLifecycle, но без
 * прокидывания контекста корутин и начального значения
 *
 * @see [https://kotlinlang.org/docs/multiplatform/compose-lifecycle.html]
 * @see [androidx.lifecycle.compose.collectAsStateWithLifecycle]
 */
@Composable
fun <TValue> FormControl<TValue, ValidationError>.collectAsComponentState(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<ComponentState<TValue>> =
    produceState(this.computeComponentState(), this) {
        lifecycle.repeatOnLifecycle(minActiveState) {
            stateChanges
                .map { state -> computeComponentState(state) }
                .distinctUntilChanged()
                .collect { x -> value = x }
        }
    }
