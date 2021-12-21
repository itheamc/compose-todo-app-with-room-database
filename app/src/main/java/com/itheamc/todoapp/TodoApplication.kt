package com.itheamc.todoapp

import android.app.Application
import com.itheamc.todoapp.repository.TodoRepository
import com.itheamc.todoapp.roomdb.TodoDatabase

class TodoApplication : Application() {
    lateinit var repository: TodoRepository

    override fun onCreate() {
        super.onCreate()
        val database: TodoDatabase by lazy {
            TodoDatabase.getInstance(this)
        }
        repository = TodoRepository(database.todoDao())
    }

}