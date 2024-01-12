package com.bottlerocketstudios.launchpad.compose.navigation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.bottlerocketstudios.launchpad.compose.resources.Dimensions
import com.bottlerocketstudios.launchpad.compose.resources.SMALL_SCREEN_WIDTH_DP
import com.bottlerocketstudios.launchpad.compose.resources.smallDimensions
import com.bottlerocketstudios.launchpad.compose.resources.sw360Dimensions

/**
 * Returns the dimensions of the screen.
 *
 * If the screen width is less than or equal to 360dp, the small dimensions are returned.
 * Otherwise, the sw360 dimensions are returned.
 */
@Composable
fun getDimensions(): Dimensions = if (LocalConfiguration.current.screenWidthDp <= SMALL_SCREEN_WIDTH_DP) smallDimensions else sw360Dimensions
