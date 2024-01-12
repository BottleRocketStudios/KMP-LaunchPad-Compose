package com.bottlerocketstudios.launchpad.compose.navigation.components

import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.toNavigationRailItems

@Composable
fun LaunchpadNavigationRail(navItems: List<NavigationItem>, isSelected: (String) -> Boolean) {
    NavigationRail {
        navItems.toNavigationRailItems(isSelected)
    }
}
