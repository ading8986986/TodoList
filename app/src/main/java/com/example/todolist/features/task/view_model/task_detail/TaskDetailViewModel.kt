package com.example.todolist.features.task.view_model.task_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.common.other.Event
import com.example.todolist.common.util.TimeFormat
import com.example.todolist.features.task.model.Task
import com.example.todolist.features.task.view_model.common.TaskSharedViewModel
import com.example.todolist.features.task.view_model.task_detail.TaskDetailViewEvent.*

class TaskDetailViewModel(private val sharedViewModel: TaskSharedViewModel) : ViewModel() {

    // flag of new created task or existing task
    private val isNewTask: Boolean = sharedViewModel.selectedTask == null

    private val _popStackEvent = MutableLiveData<Event<Boolean>>()
    val popStackEvent: LiveData<Event<Boolean>> = _popStackEvent

    //show Save button when user starts to edit
    private val _saveButtonVisibility = MutableLiveData(false)
    val saveButtonVisibility: LiveData<Boolean> = _saveButtonVisibility

    val task: Task by lazy {
        sharedViewModel.selectedTask ?: Task()
    }

    private val _taskDateString = MutableLiveData(TimeFormat.getDateFormattedString(task.date))
    val taskDateString: LiveData<String> = _taskDateString

    fun onEvent(event: TaskDetailViewEvent) {
        when (event) {
            is SaveTaskEvent -> {
                _saveButtonVisibility.value = false
                saveTaskList()
            }
            is EditDateEvent -> {
                task.date = event.date
                _saveButtonVisibility.value = true
                _taskDateString.value = TimeFormat.getDateFormattedString(task.date)
            }
            is EditTitleEvent -> {
                task.title = event.title
                _saveButtonVisibility.value = true
            }
            is EditContentEvent -> {
                task.description = event.content
                _saveButtonVisibility.value = true
            }
        }
    }

    /**
     * Save the task to the list.
     * Note: it still saves even title and content of task are empty, but it will
     * populate the Default value as "No Title"
     * **/
    private fun saveTaskList() {
        if (task.title.isEmpty()) {
            task.title = "No Title"
        }

        if (isNewTask) {
            sharedViewModel.taskLists.add(task)
        } // there is no else, because if it's not new task, the value will be updated when users edit
        // no need to do anything here

        _popStackEvent.value = Event(true)

    }
}

