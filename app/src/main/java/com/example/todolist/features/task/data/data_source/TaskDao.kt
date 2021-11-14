package com.example.todolist.features.task.data.data_source

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist.features.task.domain.model.dto.Task

@Dao
interface TaskDao {
    @Throws(SQLiteException::class)
    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Throws(SQLiteException::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)
}