package com.itheamc.todoapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry

enum class Routes(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Your Todos",
        icon = Icons.Filled.Home
    ),
    DetailScreen(
        label = "Details",
        icon = Icons.Filled.Details
    );

    companion object {
        fun fromRoute(backStackEntry: State<NavBackStackEntry?>) =
            when (backStackEntry.value?.destination?.route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                else -> HomeScreen
            }
    }
}