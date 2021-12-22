package com.itheamc.todoapp.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itheamc.todoapp.ui.screens.DetailsScreen
import com.itheamc.todoapp.ui.screens.HomeScreen
import com.itheamc.todoapp.ui.screens.NewTodoScreen
import com.itheamc.todoapp.viewmodel.TodoViewModel

@ExperimentalFoundationApi
@Composable
fun TodoNavigationHost(
    viewModel: TodoViewModel,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            composable(Routes.HomeScreen.name) {
                HomeScreen(navController = navController, viewModel = viewModel)
            }

//            composable(Routes.NewTodoScreen.name) {
//                NewTodoScreen(viewModel = viewModel)
//            }

            composable(Routes.DetailScreen.name) {
                DetailsScreen(viewModel = viewModel)
            }
        }
    )
}