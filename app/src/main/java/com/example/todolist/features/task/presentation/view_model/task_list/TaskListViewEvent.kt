package com.example.todolist.features.task.presentation.view_model.task_list

import com.example.todolist.features.task.domain.model.Task

sealed class TaskListViewEvent {
    data class ClickTaskEvent(val task: Task) : TaskListViewEvent()
    object AddTaskEvent : TaskListViewEvent()
    object GetTasksEvent : TaskListViewEvent()
}