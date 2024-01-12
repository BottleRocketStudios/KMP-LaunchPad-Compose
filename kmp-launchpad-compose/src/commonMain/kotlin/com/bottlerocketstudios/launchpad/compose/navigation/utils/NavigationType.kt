package com.bottlerocketstudios.launchpad.compose.navigation.utils

enum class NavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER, MODAL_NAVIGATION
}

/**
 * Maps a [WindowWidthSizeClass] to a [NavigationType] based on the given [DevicePosture].
 *
 * @param devicePosture The current device posture.
 * @return The corresponding [NavigationType].
 */
internal fun WindowWidthSizeClass.toNavigationType(devicePosture: DevicePosture) = when (this) {
    WindowWidthSizeClass.Compact -> NavigationType.BOTTOM_NAVIGATION
    WindowWidthSizeClass.Medium -> NavigationType.NAVIGATION_RAIL
    WindowWidthSizeClass.Expanded ->
        if (devicePosture is DevicePosture.BookPosture) NavigationType.NAVIGATION_RAIL else NavigationType.PERMANENT_NAVIGATION_DRAWER
    else -> NavigationType.BOTTOM_NAVIGATION
}
