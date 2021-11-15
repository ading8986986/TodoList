package com.example.todolist.features.task.view_model.task_detail

import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.example.todolist.common.other.Event
import com.example.todolist.common.util.TimeFormat
import com.example.todolist.features.task.model.Task
import com.example.todolist.features.task.view_model.common.TaskSharedViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import java.util.*

@SmallTest
@ExtendWith(InstantExecutorExtension::class)
class TaskDetailViewModelTest{

    private lateinit var taskDetailViewModel: TaskDetailViewModel
    private lateinit var taskSharedViewModel : TaskSharedViewModel

    private lateinit var saveButtonVisibilityObserver :Observer<Boolean>
    private lateinit var taskDateStringObserver :Observer<String>
    private lateinit var popStackEventObserver :Observer<Event<Boolean>>



    @BeforeEach
    fun setUp() {
        taskSharedViewModel = TaskSharedViewModel()
        saveButtonVisibilityObserver = mock(Observer::class.java) as Observer<Boolean>
        taskDateStringObserver = mock(Observer::class.java) as Observer<String>
        popStackEventObserver = mock(Observer::class.java) as Observer<Event<Boolean>>
    }


    @Test
    fun getTask_onSelectedTaskOfSharedViewModelIsNotEmpty() {
        val selectedTask = Task("Task Title", "Task Content", Date())
        taskSharedViewModel.selectedTask = selectedTask
        taskDetailViewModel = TaskDetailViewModel(taskSharedViewModel)
        taskDetailViewModel.saveButtonVisibility.observeForever(saveButtonVisibilityObserver)
        taskDetailViewModel.taskDateString.observeForever(taskDateStringObserver)
        assertThat(taskDetailViewModel.task).isEqualTo(selectedTask)
        verify(saveButtonVisibilityObserver).onChanged(
            false
        )
        verify(taskDateStringObserver).onChanged(
            TimeFormat.getDateFormattedString(selectedTask.date)
        )
        taskDetailViewModel.saveButtonVisibility.removeObserver(saveButtonVisibilityObserver)
        taskDetailViewModel.taskDateString.removeObserver(taskDateStringObserver)

    }

    @Test
    fun getTask_onSelectedTaskOfSharedViewModelIsEmpty() {
        taskDetailViewModel = TaskDetailViewModel(taskSharedViewModel)
        taskDetailViewModel.saveButtonVisibility.observeForever(saveButtonVisibilityObserver)
        taskDetailViewModel.taskDateString.observeForever(taskDateStringObserver)
        assertThat(taskDetailViewModel.task.title).isEmpty()
        assertThat(taskDetailViewModel.task.description).isEmpty()
        verify(saveButtonVisibilityObserver).onChanged(
            false
        )
        verify(taskDateStringObserver).onChanged(
            TimeFormat.getDateFormattedString(taskDetailViewModel.task.date)
        )
        taskDetailViewModel.saveButtonVisibility.removeObserver(saveButtonVisibilityObserver)
        taskDetailViewModel.taskDateString.removeObserver(taskDateStringObserver)
    }


    @Test
    fun onEvent_EditTitleEvent() {
        taskDetailViewModel = TaskDetailViewModel(taskSharedViewModel)
        saveButtonVisibilityObserver = mock(Observer::class.java) as Observer<Boolean>
        taskDetailViewModel.saveButtonVisibility.observeForever(saveButtonVisibilityObserver)
        taskDetailViewModel.onEvent(TaskDetailViewEvent.EditTitleEvent("Title"))
        assertThat(taskDetailViewModel.task.title).isEqualTo("Title")
        verify(saveButtonVisibilityObserver, times(1)).onChanged(
            true
        )
        taskDetailViewModel.saveButtonVisibility.removeObserver(saveButtonVisibilityObserver)
    }

    //TODO add similar tests cases for EditContentEvent & EditDataEvent

    @Test
    fun onEvent_SaveEvent_UpdateExistingTask() {
        val selectedTask = Task("Task Title", "Task Content", Date())
        taskSharedViewModel.selectedTask = selectedTask
        taskDetailViewModel = TaskDetailViewModel(taskSharedViewModel)
        taskDetailViewModel.popStackEvent.observeForever(popStackEventObserver)

        taskDetailViewModel.onEvent(TaskDetailViewEvent.EditTitleEvent("New Task Title"))
        taskDetailViewModel.onEvent(TaskDetailViewEvent.EditContentEvent("New Task Content"))

        val newDate = TimeFormat.calendarToDate(2022, 5,10)
        taskDetailViewModel.onEvent(TaskDetailViewEvent.EditDateEvent(newDate))

        taskDetailViewModel.onEvent(TaskDetailViewEvent.SaveTaskEvent)

        assertThat(taskSharedViewModel.selectedTask!!.title).isEqualTo("New Task Title")
        assertThat(taskSharedViewModel.selectedTask!!.description).isEqualTo("New Task Content")
        assertThat(taskSharedViewModel.selectedTask!!.date).isEqualTo(newDate)

        val popBackStackEvent  = taskDetailViewModel.popStackEvent.value
        assertThat(popBackStackEvent?.getContentIfNotHandled()).isNotNull()

        verify(popStackEventObserver, times(1)).onChanged(
            popBackStackEvent
        )
        taskDetailViewModel.popStackEvent.removeObserver(popStackEventObserver)
    }

    @Test
    fun onEvent_SaveEvent_AddNewTask() {
        taskDetailViewModel = TaskDetailViewModel(taskSharedViewModel)
        taskDetailViewModel.popStackEvent.observeForever(popStackEventObserver)


        taskDetailViewModel.onEvent(TaskDetailViewEvent.SaveTaskEvent)

        assertThat(taskSharedViewModel.taskLists.last()).isEqualTo(taskDetailViewModel.task)
        assertThat(taskSharedViewModel.taskLists.last().title).isEqualTo("No Title")


        val popBackStackEvent  = taskDetailViewModel.popStackEvent.value
        assertThat(popBackStackEvent?.getContentIfNotHandled()).isNotNull()

        verify(popStackEventObserver, times(1)).onChanged(
            popBackStackEvent
        )
        taskDetailViewModel.popStackEvent.removeObserver(popStackEventObserver)
    }

}

