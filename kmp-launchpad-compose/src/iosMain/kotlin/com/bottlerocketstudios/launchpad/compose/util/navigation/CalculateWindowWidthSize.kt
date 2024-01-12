package com.bottlerocketstudios.launchpad.compose.util.navigation

import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import platform.UIKit.UIWindow

fun getWindowWidthSize(uiWindow: UIWindow): WindowWidthSizeClass {
    // TODO - Logic to get window width size class on iOS
    return WindowWidthSizeClass.Compact // for iphone
//    return WindowWidthSizeClass.Expanded // for ipad
}
