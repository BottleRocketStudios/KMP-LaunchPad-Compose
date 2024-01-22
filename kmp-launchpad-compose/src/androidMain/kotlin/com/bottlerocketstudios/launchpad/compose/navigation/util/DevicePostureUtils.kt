package com.bottlerocketstudios.launchpad.compose.navigation.util

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowInfoTracker
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.FoldingFeature
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Creates a flow that emits the current device posture.
 *
 * The flow emits the following values:
 *
 * * [DevicePosture.NormalPosture] when the device is not in a book or separating posture.
 * * [DevicePosture.BookPosture] when the device is in a book posture.
 * * [DevicePosture.Separating] when the device is in a separating posture.
 *
 * The flow is lifecycle-aware and will stop emitting values when the activity is paused.
 *
 * @return The flow that emits the current device posture.
 */
fun ComponentActivity.createDevicePostureFlow() = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
    .flowWithLifecycle(this.lifecycle)
    .map { layoutInfo ->
        val foldingFeature =
            layoutInfo.displayFeatures
                .filterIsInstance<FoldingFeature>()
                .firstOrNull()
        when {
            isBookPosture(foldingFeature) ->
                DevicePosture.BookPosture(foldingFeature.bounds)

            isSeparating(foldingFeature) ->
                DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

            else -> DevicePosture.NormalPosture
        }
    }
    .stateIn(
        scope = lifecycleScope,
        started = SharingStarted.Eagerly,
        initialValue = DevicePosture.NormalPosture,
    )

/**
 * Converts an Android [WindowWidthSizeClass] to a Launchpad [WindowWidthSizeClass].
 *
 * @return The converted [WindowWidthSizeClass].
 */
internal fun WindowWidthSizeClass.toLocalModel(): com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass = when (this) {
    WindowWidthSizeClass.Compact -> com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass.Compact
    WindowWidthSizeClass.Medium -> com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass.Medium
    WindowWidthSizeClass.Expanded -> com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass.Expanded
    else -> com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass.Compact
}
