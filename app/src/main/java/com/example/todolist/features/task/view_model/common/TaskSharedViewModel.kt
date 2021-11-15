package com.example.todolist.features.task.view_model.common

import androidx.lifecycle.ViewModel
import com.example.todolist.features.task.model.Task

/**
 * Activity-Level viewModel, accessible to both TaskListFragment and TaskDetailFragment
 * **/
class TaskSharedViewModel : ViewModel() {
    var selectedTask: Task? = null
    val taskLists: MutableList<Task> = ArrayList()
}

