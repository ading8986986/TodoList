package com.example.todolist.features.task.view_model.task_list

import androidx.lifecycle.ViewModel
import com.example.todolist.features.task.model.Task
import com.example.todolist.features.task.view_model.common.TaskSharedViewModel

class TaskListViewModel(private val sharedViewModel: TaskSharedViewModel) : ViewModel() {

    // those two variables are not live data
    val taskList: List<Task> get() = sharedViewModel.taskLists
    val emptyTaskHint: Boolean
        get() = taskList.isEmpty()

}

