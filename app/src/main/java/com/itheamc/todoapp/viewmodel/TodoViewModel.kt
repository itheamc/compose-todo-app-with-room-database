package com.itheamc.todoapp.viewmodel

import androidx.lifecycle.*
import com.itheamc.todoapp.TodoApplication
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: TodoApplication) : ViewModel() {
    // Getting repository instance from the application
    private var todoRepository: TodoRepository = application.repository

    // _todo for details
    var _todo: Todo? = null

    // For selection and deletion
    private val _todos = MutableLiveData<Set<Todo>>()
    val todos: LiveData<Set<Todo>>
        get() = _todos


    // add item to selection set [_todos]
    fun select(todo: Todo) {
        _todos.postValue(todos.value?.plus(setOf(todo)) ?: setOf(todo))
    }

    // remove item from the set [_todos]
    fun deselect(todo: Todo) {
        _todos.postValue(todos.value?.minus(setOf(todo)) ?: setOf())
    }

    // deselect all
    fun deselectAll() {
        _todos.postValue(setOf())
    }



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

    /**
     * function to delete todos
     */
    fun deleteSelections() {
        if (todos.value?.isNotEmpty() == true) {
            viewModelScope.launch(Dispatchers.IO) {
                todoRepository.deleteSelections(todos.value!!.toList())
                _todos.postValue(setOf())
            }
        }
    }
}