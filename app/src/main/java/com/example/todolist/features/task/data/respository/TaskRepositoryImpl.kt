package com.example.todolist.features.task.data.respository

import android.database.sqlite.SQLiteException
import com.example.todolist.common.core.RepositoryResponse
import com.example.todolist.features.task.data.data_source.TaskDao
import com.example.todolist.features.task.domain.model.dto.Task
import com.example.todolist.features.task.domain.repository.TaskRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun getTasks(): RepositoryResponse<List<Task>> {
        return try {
            delay(1000) // mimic time consuming task
            val result = taskDao.getTasks()
            if (result.isEmpty()) RepositoryResponse.Error(
                GET_TASKS_EMPTY
            ) else
                RepositoryResponse.Success(result)
        } catch (e: SQLiteException) {
            RepositoryResponse.Error(e.message ?: GET_TASKS_EXCEPTIONS)
        }
    }

    override suspend fun addTask(task: Task): RepositoryResponse<Boolean> {
        return try {
            RepositoryResponse.Success(true)
        } catch (e: SQLiteException) {
            RepositoryResponse.Error(e.message ?: ADD_TASKS_EXCEPTIONS)
        }
    }

    companion object {
        const val GET_TASKS_EXCEPTIONS = "Catch exception on getting tasks"
        const val ADD_TASKS_EXCEPTIONS = "Catch exception on adding tasks"
        const val GET_TASKS_EMPTY = "You don't have any tasks yet, please add one"
    }
}