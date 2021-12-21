package com.itheamc.todoapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.itheamc.todoapp.models.Todo

@ExperimentalComposeUiApi
@Composable
fun NewTodoDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    onAddClick: (Todo) -> Unit,
) {

    var title by rememberSaveable {
        mutableStateOf("")
    }

    var desc by rememberSaveable {
        mutableStateOf("")
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(375)),
        exit = fadeOut(animationSpec = tween(375))
    ) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                securePolicy = SecureFlagPolicy.SecureOn
            ),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = "Title",
                        placeholder = "Enter your title..",
                        keyboardType = KeyboardType.Text
                    )

                    Spacer8()

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        value = desc,
                        onValueChange = {
                            desc = it
                        },
                        label = "Description",
                        placeholder = "Enter your description..",
                        keyboardType = KeyboardType.Text
                    )

                    Spacer8()

                    // Add new todo button
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
                        ),
                        onClick = {
                            onAddClick(
                                Todo(
                                    _id = 0,
                                    _title = title,
                                    _desc = desc
                                )
                            )
                            title = ""
                            desc = ""
                        }
                    ) {
                        Text(text = "Add Todo")
                    }
                }
            }
        )
    }
}