package com.itheamc.todoapp.repository

import androidx.annotation.WorkerThread
import com.itheamc.todoapp.models.Todo
import com.itheamc.todoapp.roomdb.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: Flow<List<Todo>> = todoDao.allTodos()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTodo(todo: Todo) {
        todoDao.addTodo(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteSelections(list: List<Todo>) {
        todoDao.deleteSelections(list)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

}