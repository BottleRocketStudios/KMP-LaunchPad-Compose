package com.bottlerocketstudios.launchpad.compose.navigation.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class WindowWidthSizeClass private constructor(private val value: Int) : Comparable<WindowWidthSizeClass> {

    override fun compareTo(other: WindowWidthSizeClass) = value.compareTo(other.value)

    override fun toString(): String {
        return "WindowWidthSizeClass." + when (this) {
            Compact -> "Compact"
            Medium -> "Medium"
            Expanded -> "Expanded"
            else -> ""
        }
    }

    companion object {
        /** Represents the majority of phones in portrait. */
        val Compact = WindowWidthSizeClass(0)

        /**
         * Represents the majority of tablets in portrait and large unfolded inner displays in
         * portrait.
         */
        val Medium = WindowWidthSizeClass(1)

        /**
         * Represents the majority of tablets in landscape and large unfolded inner displays in
         * landscape.
         */
        val Expanded = WindowWidthSizeClass(2)

        /** Calculates the [WindowWidthSizeClass] for a given [width] */
        internal fun fromWidth(width: Dp): WindowWidthSizeClass {
            require(width >= 0.dp) { "Width must not be negative" }
            return when {
                width < 600.dp -> Compact
                width < 840.dp -> Medium
                else -> Expanded
            }
        }
    }
}

enum class WindowOrientation {
    Portrait,
    Landscape
}
