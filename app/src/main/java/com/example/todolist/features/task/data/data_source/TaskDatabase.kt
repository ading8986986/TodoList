package com.example.todolist.features.task.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.features.task.domain.model.dto.Task

@Database(
    entities = [Task::class],
    version = 1
)
@TypeConverters(TaskConverter::class)

abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATA_BASE_NAME = "task_database"
    }
}