package com.itheamc.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.ui.components.Spacer12
import com.itheamc.todoapp.ui.components.Spacer6
import com.itheamc.todoapp.viewmodel.TodoViewModel

@Composable
fun NewTodoScreen(viewModel: TodoViewModel) {

    val scope = rememberCoroutineScope()

    var title by rememberSaveable {
        mutableStateOf("")
    }

    var desc by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxSize(0.75f)
                .height(48.dp),
            value = title,
            onValueChange = {
                title = it
            },
            cursorBrush = Brush.linearGradient(
                colors = listOf(
                    Color.Cyan,
                    Color.Magenta,
                    Color.Yellow,
                    Color.Red
                )
            )
        )

        Spacer6()

        BasicTextField(
            modifier = Modifier
                .fillMaxSize(0.75f)
                .height(250.dp),
            value = desc,
            onValueChange = {
                desc = it
            },
            cursorBrush = Brush.linearGradient(
                colors = listOf(
                    Color.Cyan,
                    Color.Magenta,
                    Color.Yellow,
                    Color.Red
                )
            )
        )

        Spacer12()
        Button(
            onClick = {
                viewModel.addTodo(
                    Todo(
                        _id = 0,
                        _title = title,
                        _desc = desc
                    )
                )
            }
        ) {
            Text(text = "Add Now")
        }
    }
}