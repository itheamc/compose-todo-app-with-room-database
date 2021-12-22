package com.itheamc.todoapp.roomdb

import androidx.room.*
import com.itheamc.todoapp.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    /**
     * To get all the todos
     */
    @Query("SELECT * FROM todos")
    fun allTodos(): Flow<List<Todo>>

    /**
     * Function to insert [todo] to our database
     */
    @Insert
    suspend fun addTodo(todo: Todo)

    /**
     * Function to delete [todo] from our database
     */
    @Delete
    suspend fun deleteTodo(todo: Todo)

    /**
     * Function to delete list of todos
     */
    @Delete
    suspend fun deleteSelections(list: List<Todo>)

    /**
     * Function to update [todo] from our database
     */
    @Update
    suspend fun updateTodo(todo: Todo)
}