package com.bottlerocketstudios.launchpad.compose.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadBottomAppBar
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadDrawerContent
import com.bottlerocketstudios.launchpad.compose.navigation.components.LaunchpadNavigationRail
import com.bottlerocketstudios.launchpad.compose.navigation.utils.DevicePosture
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationType
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import com.bottlerocketstudios.launchpad.compose.navigation.utils.generateNavItems
import com.bottlerocketstudios.launchpad.compose.navigation.utils.toNavigationType

@Suppress("MemberVisibilityCanBePrivate")
class NavWrapperState {
    var showNavigation by mutableStateOf(true)
    var showNavigationRail by mutableStateOf(true)
    var showBottomBar by mutableStateOf(true)
    var showPermanentDrawer by mutableStateOf(true)
    var showModalDrawer by mutableStateOf(true)

    val shouldShowNavigationRail @Composable get() = showNavigationRail && showNavigation
    val shouldShowBottomBar @Composable get() = showBottomBar && showNavigation
    val shouldShowPermanentDrawer @Composable get() = showPermanentDrawer && showNavigation
    val shouldShowModalDrawer @Composable get() = showModalDrawer && showNavigation
}

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
    navState: NavWrapperState = NavWrapperState(),
    app: @Composable (navigator: NavHostController) -> Unit
) {
    // Setup State
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigator = rememberNavController()

    val currentBackStackEntry by navigator.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

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

    when {
        // Modal Drawer
        navigationType.value == NavigationType.MODAL_NAVIGATION && navState.shouldShowModalDrawer ->
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = { ModalDrawerSheet { LaunchpadDrawerContent(navItems) { it == currentRoute } } }
            ) { app(navigator) }

        //  Permanent Drawer
        navigationType.value == NavigationType.PERMANENT_NAVIGATION_DRAWER && navState.shouldShowPermanentDrawer ->
            PermanentNavigationDrawer(
                drawerContent = { PermanentDrawerSheet { LaunchpadDrawerContent(navItems) { it == currentRoute } } }
            ) { app(navigator) }

        // Remaining states are animated in out around one central instance of app.
        else ->
            Row {
                // Nav Rail
                AnimatedVisibility(
                    visible = navigationType.value == NavigationType.NAVIGATION_RAIL && navState.shouldShowNavigationRail,
                    enter = slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)),
                    exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh))
                ) {
                    LaunchpadNavigationRail(navItems) { it == currentRoute }
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    // App content
                    Box(modifier = Modifier.weight(1f)) {
                        app(navigator)
                    }

                    // Bottom Bar
                    AnimatedVisibility(
                        visible = navigationType.value == NavigationType.BOTTOM_NAVIGATION && navState.shouldShowBottomBar,
                        enter = slideInVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)),
                        exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh))
                    ) {
                        LaunchpadBottomAppBar(navItems) { it == currentRoute }
                    }
                }
            }

    }
}
