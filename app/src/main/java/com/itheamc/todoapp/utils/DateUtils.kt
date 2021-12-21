package com.itheamc.todoapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String {
    return SimpleDateFormat.getDateInstance().format(Date(this))
}