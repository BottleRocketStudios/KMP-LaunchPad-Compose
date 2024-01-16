package com.bottlerocketstudios.launchpad.compose.navigation.utils

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A data class representing a navigation item.
 *
 * @property route The route to navigate to when the item is clicked.
 * @property onClick The callback to be called when the item is clicked.
 * @property selectedIcon The icon to be displayed when the item is selected.
 * @property unselectedIcon The icon to be displayed when the item is not selected.
 * @property label The label to be displayed for the item.
 * @property modifier The modifier to be applied to the item.
 * @property enabled Whether the item is enabled.
 */
data class NavigationItem(
    val route: String,
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit,
    val label: @Composable () -> Unit,
    val modifier: Modifier = Modifier,
    val enabled: Boolean = true,
    val onClick: (NavigationItem) -> Unit = {},
)

/**
 * Converts a [NavigationItem] to a [NavigationDrawerItem].
 *
 * @param selected Whether the item is selected.
 * @return The converted [NavigationDrawerItem].
 */
@Composable
internal fun NavigationItem.toNavigationDrawerItem(selected: Boolean = false) =
    NavigationDrawerItem(
        label = label,
        selected = selected,
        onClick = { onClick(this) },
        icon = if (selected) selectedIcon else unselectedIcon,
        modifier = modifier
    )

/**
 * Converts a list of [NavigationItem]s to a list of [NavigationDrawerItem]s.
 *
 * @param isSelected A function that determines whether a navigation item is selected.
 */
@Composable
internal fun List<NavigationItem>.toNavigationDrawerItems(isSelected: (String) -> Boolean) =
    forEach { navigationItem ->
        navigationItem.toNavigationDrawerItem(selected = isSelected(navigationItem.route))
    }

/**
 * Converts a [NavigationItem] to a [NavigationRailItem].
 *
 * @param selected Whether the item is selected.
 * @return A [NavigationRailItem] that represents the [NavigationItem].
 */
@Composable
internal fun NavigationItem.toNavigationRailItem(selected: Boolean = false) = NavigationRailItem(
    selected = selected,
    onClick = { onClick(this) },
    icon = if (selected) selectedIcon else unselectedIcon,
    label = label,
    modifier = modifier,
    enabled = enabled
)

/**
 * Converts a list of [NavigationItem]s to a list of [NavigationRailItem]s.
 *
 * @param isSelected A function that determines whether a [NavigationItem] is selected.
 */
@Composable
internal fun List<NavigationItem>.toNavigationRailItems(isSelected: (String) -> Boolean) =
    forEach { navigationItem ->
        navigationItem.toNavigationRailItem(selected = isSelected(navigationItem.route))
    }

/**
 * A composable function that converts a [NavigationItem] into a [NavigationBarItem].
 *
 * @param item The navigation item to convert.
 * @param selected Whether the navigation item is selected.
 */
@Composable
internal fun RowScope.toNavigationBarItem(item: NavigationItem, selected: Boolean = false) {
    with(item) {
        NavigationBarItem(
            selected = selected,
            onClick = { onClick(this) },
            icon = if (selected) selectedIcon else unselectedIcon,
            label = label,
            modifier = modifier,
            enabled = enabled
        )
    }
}

/**
 * Composes a row of navigation items.
 *
 * @param items The list of navigation items.
 * @param isSelected A function that returns true if the given route is selected.
 */
@Composable
internal fun RowScope.ToNavigationBarItems(items: List<NavigationItem>, isSelected: (String) -> Boolean) {
    items.forEach { navigationItem ->
        toNavigationBarItem(
            item = navigationItem,
            selected = isSelected(navigationItem.route)
        )
    }
}
