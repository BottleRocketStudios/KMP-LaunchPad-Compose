package com.bottlerocketstudios.launchpad.compose.example.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.bottlerocketstudios.launchpad.compose.navigation.utils.NavigationItem

val exampleNavigationItems = listOf(
    NavigationItem(
        route = "/home",
        selectedIcon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "") },
        unselectedIcon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = "") },
        label = { Text("Home") },
    ),
    NavigationItem(
        route = "/settings",
        selectedIcon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = "") },
        unselectedIcon = { Icon(imageVector = Icons.Outlined.Settings, contentDescription = "") },
        label = { Text("Settings") },
    ),
    NavigationItem(
        route = "/profile",
        selectedIcon = { Icon(imageVector = Icons.Filled.VerifiedUser, contentDescription = "") },
        unselectedIcon = { Icon(imageVector = Icons.Outlined.VerifiedUser, contentDescription = "") },
        label = { Text("Profile") },
    )
)
