package com.itheamc.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itheamc.todoapp.TodoApplication
import java.lang.IllegalArgumentException

class TodoViewModelFactory(private val application: TodoApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}