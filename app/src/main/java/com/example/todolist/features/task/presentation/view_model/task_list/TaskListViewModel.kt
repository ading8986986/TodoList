package com.example.todolist.features.task.presentation.view_model.task_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.common.core.UseCaseResponse.*
import com.example.todolist.features.task.domain.use_cases.GetTasksUseCase
import com.example.todolist.features.task.presentation.view_model.task_list.TaskListViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<TaskListViewState>()
    val state: LiveData<TaskListViewState> = _state


    fun onEvent(event: TaskListViewEvent) {
        when (event) {
            is ClickTaskEvent -> {
            }
            is AddTaskEvent -> {
            }
            is GetTasksEvent ->{
                getTaskLists()
            }
        }
    }

    private fun getTaskLists(){
        viewModelScope.launch {
            getTasksUseCase().collect {
                when (it) {
                    is Loading -> {
                        _state.value = TaskListViewState(loadingVisibility = true)
                    }
                    is Success -> _state.value = TaskListViewState(it.data)
                    is Error -> {
                        _state.value = TaskListViewState(errorSnackBarContent = it.message)
                    }
                }
            }
        }
    }
}

