package com.itheamc.todoapp.ui.scaffold

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.ui.components.NewTodoDialog
import com.itheamc.todoapp.ui.navigation.Routes
import com.itheamc.todoapp.ui.navigation.TodoNavigationHost
import com.itheamc.todoapp.viewmodel.TodoViewModel

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun TodoScaffold(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = Routes.fromRoute(backstackEntry)


    val selections: Set<Todo> by viewModel.todos.observeAsState(setOf())

    // For New Todo Dialog
    var visible by rememberSaveable {
        mutableStateOf(false)
    }

    // Controlling system back click behaviour if item is selected
    BackHandler(
        enabled = selections.isNotEmpty(),
        onBack = {
            viewModel.deselectAll()
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = currentScreen.label)
                },
                actions = {
                    if (selections.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                viewModel.deleteSelections()
                            }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "delete"
                                )
                                Text(
                                    text = selections.size.toString(),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.surface.copy(
                                            alpha = 0.75f
                                        ),
                                        fontSize = 8.sp
                                    )
                                )
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = if (currentScreen == Routes.HomeScreen) {
            {
                SmallFloatingActionButton(
                    onClick = {
                        visible = true
                        viewModel.deselectAll()
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