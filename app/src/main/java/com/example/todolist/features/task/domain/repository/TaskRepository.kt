package com.example.todolist.features.task.domain.repository

import com.example.todolist.common.core.RepositoryResponse
import com.example.todolist.features.task.domain.model.dto.Task

interface TaskRepository {
    suspend fun getTasks(): RepositoryResponse<List<Task>>
    suspend fun addTask(task: Task): RepositoryResponse<Boolean>
}