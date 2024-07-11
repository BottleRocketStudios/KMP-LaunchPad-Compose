package com.bottlerocketstudios.launchpad.compose.util

import androidx.compose.ui.window.WindowState
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass

fun WindowState.getWindowWidthSizeClass() =
    when (size.width.value.toLong()) {
        in 840..Int.MAX_VALUE -> WindowWidthSizeClass.Expanded
        in 600..840 -> WindowWidthSizeClass.Medium
        in Int.MIN_VALUE..600 -> WindowWidthSizeClass.Compact
        else -> WindowWidthSizeClass.Medium
    }