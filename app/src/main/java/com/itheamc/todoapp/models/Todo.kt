package com.itheamc.todoapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val _id: Int,
    @ColumnInfo(name = "todo_title") val _title: String,
    @ColumnInfo(name = "todo_desc") val _desc: String,
    @ColumnInfo(name = "added_on") val _date: Long = Date().time
)