package com.example.todolist.features.task.view_model.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskSharedViewModelFactory(private val sharedViewModel: TaskSharedViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TaskSharedViewModel::class.java).newInstance(sharedViewModel)
    }
}