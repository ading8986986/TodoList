package com.example.todolist.features.task.presentation.view_model.task_list

import com.example.todolist.features.task.domain.model.Task

data class TaskListViewState(
    val taskList: List<Task> = emptyList(),
    val loadingVisibility: Boolean = false,
    val errorSnackBarContent: String? = null
) {

}
