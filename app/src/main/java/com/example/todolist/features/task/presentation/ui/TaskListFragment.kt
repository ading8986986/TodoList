package com.example.todolist.features.task.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentTaskListBinding
import com.example.todolist.features.task.domain.model.Task
import com.example.todolist.features.task.presentation.component.TaskListAdapter
import com.example.todolist.features.task.presentation.view_model.task_list.TaskListViewEvent
import com.example.todolist.features.task.presentation.view_model.task_list.TaskListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by viewModels()
    private lateinit var viewBinding: FragmentTaskListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        viewBinding.recyclerView.adapter = TaskListAdapter {
            viewModel.onEvent(TaskListViewEvent.ClickTaskEvent(it))
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(TaskListViewEvent.GetTasksEvent)

        // add listener
        viewBinding.addButton.setOnClickListener {
            navigateToTaskDetailFragment()
        }
        // add observer
        viewModel.state.observe(viewLifecycleOwner) {
            updateTaskList(it.taskList)
            updateErrorView(it.errorSnackBarContent)
            updateLoadingView(it.loadingVisibility)
        }
    }

    private fun updateTaskList(tasks: List<Task>) {
        (viewBinding.recyclerView.adapter as TaskListAdapter).taskList = tasks
    }

    private fun updateErrorView(error: String?) {
        error?.let {
            Snackbar.make(viewBinding.root, error, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateLoadingView(visibility: Boolean) {
        if (viewBinding.loadingView.isVisible != visibility) {
            viewBinding.loadingView.isVisible = visibility
        }
    }

    private fun navigateToTaskDetailFragment(task: Task? = null) {
        val action = TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(task)
        findNavController().navigate(action)
    }
}