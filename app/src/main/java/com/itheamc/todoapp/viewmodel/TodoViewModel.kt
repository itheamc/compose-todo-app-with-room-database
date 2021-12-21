package com.itheamc.todoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itheamc.todoapp.TodoApplication
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: TodoApplication) : ViewModel() {

    var _todo: Todo? = null


    private var todoRepository: TodoRepository = application.repository


    /**
     * Constant Variable to store the observable todos
     */
    val allTodos: LiveData<List<Todo>> = todoRepository.allTodos.asLiveData()

    /**
     * function to add todo
     */
    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(todo)
        }
    }

    /**
     * function to update todo
     */
    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    /**
     * function to delete todo
     */
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todo)
        }
    }
}