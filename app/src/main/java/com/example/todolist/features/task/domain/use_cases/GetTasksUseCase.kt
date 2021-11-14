package com.example.todolist.features.task.domain.use_cases

import com.example.todolist.common.core.UseCaseResponse
import com.example.todolist.common.core.RepositoryResponse
import com.example.todolist.features.task.domain.model.dto.toTaskListItemModel
import com.example.todolist.features.task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(): Flow<UseCaseResponse<List<TaskListItemModel>>> {
        return flow {
            emit(UseCaseResponse.Loading)
            repository.getTasks().let {
                when (it) {
                    is RepositoryResponse.Success -> {
                        // prepare data directly needed by viewModel
                        emit(UseCaseResponse.Success(it.data.map { task ->
                            task.toTaskListItemModel()
                        }))
                    }
                    is RepositoryResponse.Error -> emit(UseCaseResponse.Error(it.message))
                }
            }
        }
    }
}