package com.bottlerocketstudios.launchpad.compose.navigation.components

import androidx.compose.runtime.Composable
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.toNavigationDrawerItems

@Composable
fun LaunchpadDrawerContent(navItems: List<NavigationItem>, isSelected: (String) -> Boolean) {
    navItems.toNavigationDrawerItems(isSelected)
}
