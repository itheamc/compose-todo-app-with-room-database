package com.itheamc.todoapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.utils.toDate
import com.itheamc.todoapp.viewmodel.TodoViewModel

@Composable
fun DetailsScreen(viewModel: TodoViewModel) {
    val todo: Todo? = viewModel._todo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = if (todo == null) Arrangement.Center else Arrangement.spacedBy(8.dp),
    ) {
        todo?.let {
            // Title
            Text(
                text = it._title,
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            // Desc
            Text(
                text = it._desc,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            // Date
            Text(
                text = "Added on ${it._date.toDate()}",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}