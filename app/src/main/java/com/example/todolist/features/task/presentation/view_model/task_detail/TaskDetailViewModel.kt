package com.example.todolist.features.task.presentation.view_model.task_detail

import androidx.lifecycle.ViewModel
import com.example.todolist.features.task.domain.use_cases.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val addTaskUseCase: AddTaskUseCase) :
    ViewModel() {


}