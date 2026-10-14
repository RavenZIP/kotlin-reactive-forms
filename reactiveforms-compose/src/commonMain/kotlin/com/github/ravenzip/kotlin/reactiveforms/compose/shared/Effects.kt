package com.github.ravenzip.kotlin.reactiveforms.compose.shared

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.ravenzip.krex.function.pairwise
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Composable
fun FocusLostEffect(interactionSource: MutableInteractionSource, onFocusLost: () -> Unit) {
    LaunchedEffect(interactionSource) {
        interactionSource.interactions
            .map { interaction ->
                interaction is FocusInteraction.Focus
            }
            .pairwise()
            .filter { x -> !x.second }
            .onEach { onFocusLost() }
            .launchIn(this)
    }
}
