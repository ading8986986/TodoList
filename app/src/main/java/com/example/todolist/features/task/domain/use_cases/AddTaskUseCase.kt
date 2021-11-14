package com.example.todolist.features.task.domain.use_cases

import com.example.todolist.common.core.RepositoryResponse
import com.example.todolist.common.core.UseCaseResponse
import com.example.todolist.features.task.domain.model.dto.Task
import com.example.todolist.features.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: TaskRepository)  {
    suspend operator fun invoke(request: Task): Flow<UseCaseResponse<Boolean>> {
        return flow {
            emit(UseCaseResponse.Loading)
            repository.addTask(request).let {
                when (it) {
                    is RepositoryResponse.Success -> emit(UseCaseResponse.Success(it.data))
                    is RepositoryResponse.Error -> emit(UseCaseResponse.Error(it.message))
                }
            }
        }
    }
}