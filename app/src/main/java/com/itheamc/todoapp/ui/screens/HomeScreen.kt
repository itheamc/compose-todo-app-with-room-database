package com.itheamc.todoapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.ui.components.TodoView
import com.itheamc.todoapp.ui.navigation.Routes
import com.itheamc.todoapp.viewmodel.TodoViewModel


@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController, viewModel: TodoViewModel) {
    val allTodos: List<Todo> by viewModel.allTodos.observeAsState(listOf())

    val lazyListState = rememberLazyListState()
    var selectedItems: Set<Todo> by rememberSaveable {
        mutableStateOf(setOf())
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = lazyListState,
        content = {
            items(allTodos) { todo ->
                TodoView(
                    selected = selectedItems.contains(todo),
                    todo = todo,
                    onClick = {
                        if (selectedItems.contains(todo)) {
                            selectedItems = selectedItems.minus(todo)
                        } else {
                            if (selectedItems.isEmpty()) {
                                viewModel._todo = todo
                                navController.navigate(Routes.DetailScreen.name)
                            } else {
                                selectedItems = selectedItems.plus(todo)
                            }
                        }
                    },
                    onLongClick = {
                        selectedItems = selectedItems.plus(todo)
                    }
                )
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
}