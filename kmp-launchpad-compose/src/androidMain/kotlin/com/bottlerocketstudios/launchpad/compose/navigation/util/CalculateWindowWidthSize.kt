package com.bottlerocketstudios.launchpad.compose.navigation.util

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass

/**
 * Gets the window width size class of the given activity.
 *
 * @param activity The activity to get the window width size class for.
 * @return The window width size class of the activity.
 */
@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getWindowWidthSize(activity: Activity): WindowWidthSizeClass = calculateWindowSizeClass(activity).widthSizeClass.toLocalModel()
