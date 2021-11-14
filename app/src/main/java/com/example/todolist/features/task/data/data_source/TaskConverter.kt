package com.example.todolist.features.task.data.data_source

import androidx.room.TypeConverter
import java.util.*

class TaskConverter {
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time?.toLong()
    }
}