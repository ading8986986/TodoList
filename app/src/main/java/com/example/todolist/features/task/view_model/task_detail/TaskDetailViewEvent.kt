package com.example.todolist.features.task.view_model.task_detail

import java.util.*

/**
 * All user interaction events from TaskDetailScreen
 * **/
sealed class TaskDetailViewEvent {
    object SaveTaskEvent : TaskDetailViewEvent()
    data class EditTitleEvent(val title: String) : TaskDetailViewEvent()
    data class EditContentEvent(val content: String) : TaskDetailViewEvent()
    data class EditDateEvent(val date: Date) :
        TaskDetailViewEvent()
}