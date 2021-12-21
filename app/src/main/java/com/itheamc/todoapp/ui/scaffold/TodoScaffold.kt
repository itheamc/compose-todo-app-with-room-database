package com.itheamc.todoapp.ui.scaffold

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itheamc.todoapp.ui.components.NewTodoDialog
import com.itheamc.todoapp.ui.navigation.Routes
import com.itheamc.todoapp.ui.navigation.TodoNavigationHost
import com.itheamc.todoapp.viewmodel.TodoViewModel

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun TodoScaffold(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = Routes.fromRoute(backstackEntry)

    // For New Todo Dialog
    var visible by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = currentScreen.label)
                }
            )
        },
        floatingActionButton = if (currentScreen == Routes.HomeScreen) {
            {
                SmallFloatingActionButton(
                    onClick = {
                        visible = true
                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Todo")
                    }
                )
            }
        } else {
            {}
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            TodoNavigationHost(
                viewModel = viewModel,
                navController = navController,
                startDestination = Routes.HomeScreen.name
            )

            // Dialog
            NewTodoDialog(
                visible = visible,
                onDismiss = {
                    visible = false
                },
                onAddClick = {
                    viewModel.addTodo(it)
                    Log.d(TAG, "TodoScaffold: $it")
                }
            )
        }
    )
}


//Tag
private const val TAG: String = "TodoScaffold"