package com.bottlerocketstudios.launchpad.compose.navigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.ToNavigationBarItems

@Composable
fun LaunchpadBottomAppBar(
    navItems: List<NavigationItem>,
    isSelected: (String) -> Boolean
) {
    NavigationBar(
        Modifier
            .fillMaxWidth()
    ) {
        ToNavigationBarItems(navItems, isSelected)
    }
}
