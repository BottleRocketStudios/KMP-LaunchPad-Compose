package com.bottlerocketstudios.launchpad.compose.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadBottomAppBar
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadDrawerContent
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadNavigationRail
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationType
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.bottlerocketstudios.launchpad.compose.navigation.utils.generateNavItems
import com.bottlerocketstudios.launchpad.compose.navigation.utils.toNavigationType
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

/**
 * A composable function that wraps the Precompose navigation component.
 *
 * Using this Navigation Wrapper requires the Precompose library on your project.
 *
 * @param widthSize The current window width size class.
 * @param devicePosture The current device posture.
 * @param navigationItems The list of navigation items, these will be the items that show up on the navigation rail or bottom navigation bar.
 * For an example of this please see [exampleNavigationItems] and the [NavigationItem] class.
 * @param app The function that renders the app content.
 */
@Composable
fun NavigationWrapper(
    widthSize: WindowWidthSizeClass,
    devicePosture: DevicePosture,
    navigationItems: List<NavigationItem>,
    app: @Composable (navigator: Navigator?, bottomBar: (@Composable () -> Unit)) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigator = rememberNavigator()
    val currentBackStackEntry by navigator.currentEntry.collectAsState(null)
    val currentRoute = currentBackStackEntry?.route
    val navItems = remember {
        generateNavItems(navigationItems) {
            navigator.navigate(it.route)
        }
    }
    val navigationType = remember(widthSize, devicePosture) {
        derivedStateOf {
            widthSize.toNavigationType(devicePosture)
        }
    }

    when (navigationType.value) {
        NavigationType.PERMANENT_NAVIGATION_DRAWER ->
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet() { LaunchpadDrawerContent(navItems) { it == currentRoute?.route } }
                }
            ) { app(navigator) { } }

        NavigationType.MODAL_NAVIGATION ->
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = { ModalDrawerSheet { LaunchpadDrawerContent(navItems) { it == currentRoute?.route } } }
            ) { app(navigator) { } }

        else -> Row {
            AnimatedVisibility(
                visible = navigationType.value == NavigationType.NAVIGATION_RAIL,
                enter = slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)),
                exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh))
            ) {
                LaunchpadNavigationRail(navItems) {
                    it == currentRoute?.route
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                app(navigator) {
                    AnimatedVisibility(
                        visible = navigationType.value == NavigationType.BOTTOM_NAVIGATION,
                        enter = slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)),
                        exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh))
                    ) {
                        LaunchpadBottomAppBar(navItems) { it == currentRoute?.route }
                    }
                }
            }
        }
    }
}
