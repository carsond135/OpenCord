package com.xinto.opc

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

public enum class OverlappingPanelsCompose {
    OpenStart,
    OpenEnd,
    Closed;
}

public class OverlappingPanelsState(
    initialValue: OverlappingPanelsValue,
    confirmStateChange: (OverlappingPanelsValue) -> Boolean = { true },
) {
    public val swipeableState: SwipeableState<OverlappingPanelsValue> =
        SwipeableState(
            initialValue = initialValue,
            animationSpec = spring(),
            confirmStateChange = confirmStateChange
        )

    public val currentValue: OverlappingPanelsValue
        get() = swipeableState.currentValue

    public val targetValue: OverlappingPanelsValue
        get() = swipeableState.targetValue

    public val offset: State<Float>
        get() = swipeableState.offset

    public val offsetIsPositive: Boolean
        get() = offset.value > 0f

    public val offsetIsNegative: Boolean
        get() = offset.value < 0f

    public val offsetNotZero: Boolean
        get() = offset.value != 0f

    public val panelsClosed: Boolean
        get() = currentValue == OverlappingPanelsValue.Closed

    public val endPanelOpen: Boolean
        get() = currentValue == OverlappingPanelsValue.OpenStart

    public val startPanelOpen: Boolean
        get() = currentValue == OverlappingPanelsValue.OpenEnd

    public suspend fun openStartPanel() {
        swipeableState.animateTo(OverlappingPanelsValue.OpenEnd)
    }

    public suspend fun openEndPanel() {
        swipeableState.animateTo(OverlappingPanelsValue.OpenStart)
    }

    public suspend fun closePanels() {
        swipeableState.animateTo(OverlappingPanelsValue.Closed)
    }

    public companion object {
        public fun Saver(
            confirmStateChange: (OverlappingPanelsValue) -> Boolean
        ): Saver<OverlappingPanelsState, OverlappingPanelsValue> {
            return Saver(
                save = { it.currentValue },
                restore = { OverlappingPanelsState(it, confirmStateChange) }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
public fun rememberOverlappingPanelsState(
    initialValue: OverlappingPanelsValue = OverlappingPanelsValue.Close,
    confirmStateChange: (OverlappingPanelsValue) -> Boolean = { true },
): OverlappingPanelsState {
    return rememberSaveable(saver = OverlappingPanelsState.Saver(confirmStateChange)) {
        OverlappingPanelsState(initialValue, confirmStateChange)
    }
}

@ExperimentalMaterialApi
@Composable
public fun OverlappingPanels(
    panelStart: @Composable BoxScope.() -> Unit,
    panelCenter: @Composable BoxScope.() -> Unit,

)