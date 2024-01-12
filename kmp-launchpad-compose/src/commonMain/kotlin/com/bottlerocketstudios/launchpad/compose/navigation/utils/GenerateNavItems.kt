package com.bottlerocketstudios.launchpad.compose.navigation.utils

/**
 * Generates a list of navigation items with the provided onClick callback.
 *
 * @param items The list of navigation items.
 * @param onClick The callback to be called when an item is clicked.
 * @return A list of navigation items with the provided onClick callback.
 */
internal fun generateNavItems(items: List<NavigationItem>, onClick: (NavigationItem) -> Unit) = items.map { it.copy(onClick = onClick) }
