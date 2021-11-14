package com.example.todolist.features.task.presentation.view_model.task_detail

import com.example.todolist.features.task.domain.model.Task

sealed class TaskDetailViewEvent {
    data class SaveTaskEvent(val task: Task) : TaskDetailViewEvent()
}
