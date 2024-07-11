package com.bottlerocketstudios.launchpad.compose.example.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bottlerocketstudios.launchpad.compose.navigation.utils.WindowWidthSizeClass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleApp(
    widthSize: WindowWidthSizeClass,
    navigator: NavHostController,
//    bottomBar: @Composable () -> Unit
) {
    // FIXME - after testing
//    Column(
//        topBar = { TopAppBar(title = { Text("Example App") }) },
//        bottomBar = bottomBar
//    ) {
//        NavHost(
//            navController = navigator,
//            startDestination = "/home",
//            modifier = Modifier.padding(it)
//        ) {
//            // Navgraph goes here
//        }
//    }
}

