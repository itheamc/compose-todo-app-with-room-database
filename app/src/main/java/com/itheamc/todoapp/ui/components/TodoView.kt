package com.itheamc.todoapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.utils.toDate

@ExperimentalFoundationApi
@Composable
fun TodoView(
    selected: Boolean,
    todo: Todo,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {

    val fraction: Float by animateFloatAsState(
        targetValue = if (selected) 0.90f else 0.95f,
        animationSpec = tween(375)
    )

    val alpha by animateFloatAsState(
        targetValue = if (selected) 0.30f else 0.1f,
        animationSpec = tween(375)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = alpha
                )
            )
            .combinedClickable(
                enabled = true,
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Title
        Text(
            text = todo._title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold
            )
        )

        // Desc
        Text(
            text = todo._desc,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Date
        Text(
            text = "Added on ${todo._date.toDate()}",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic
            )
        )
    }
}