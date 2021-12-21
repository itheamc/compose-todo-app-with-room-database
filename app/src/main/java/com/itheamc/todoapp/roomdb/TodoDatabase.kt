package com.itheamc.todoapp.roomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itheamc.todoapp.TodoApplication
import com.itheamc.todoapp.models.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    /**
     * Abstract function to return the instance of the TodoDao
     */
    abstract fun todoDao(): TodoDao

    /**
     * Static variable and function
     */
    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        /**
         * Singleton function to return the instance of the database
         */
        fun getInstance(application: TodoApplication): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    TodoDatabase::class.java,
                    "my_todos"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}