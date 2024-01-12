package com.bottlerocketstudios.launchpad.compose.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.bottlerocketstudios.launchpad.compose.navigation.NavigationWrapper
import com.bottlerocketstudios.launchpad.compose.navigation.util.createDevicePostureFlow
import com.bottlerocketstudios.launchpad.compose.navigation.util.getWindowWidthSize
import moe.tlaster.precompose.PreComposeApp

class MainActivityExample : ComponentActivity() {

    // Create a flow that emits the current device posture.
    private val devicePostureFlow = createDevicePostureFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Collects the device posture flow and stores it in a state variable.
            val devicePosture = devicePostureFlow.collectAsState()

            PreComposeApp {
                // In this example, the NavWrapper for the Precompose Navigation library is being used.
                // The app param will extend the wrappers navigation component and bottom bar allowing both to be used in App Composable.
                NavigationWrapper(
                    widthSize = getWindowWidthSize(this),
                    devicePosture = devicePosture.value,
                    navigationItems = exampleNavigationItems
                ) { navigator, bottomBar ->
                    ExampleApp(
                        widthSize = getWindowWidthSize(this),
                        navigator = navigator,
                        bottomBar = bottomBar
                    )
                }
            }
        }
    }
}
