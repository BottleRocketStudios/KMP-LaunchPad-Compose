package com.bottlerocketstudios.launchpad.compose.example.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.transition.NavTransition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleApp(
    widthSize: WindowWidthSizeClass,
    navigator: Navigator?,
    bottomBar: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Example App") }) },
        bottomBar = bottomBar
    ) {
        navigator?.let { navController ->
            NavHost(
                // Assign the navigator to the NavHost
                navigator = navController,
                // Navigation transition for the scenes in this NavHost, this is optional
                navTransition = NavTransition(),
                // The start destination
                initialRoute = "/home",
                modifier = Modifier.padding(it)
            ) {
                // Navgraph goes here
            }
        }
    }
}
