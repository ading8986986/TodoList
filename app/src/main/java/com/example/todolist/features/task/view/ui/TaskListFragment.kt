package com.example.todolist.features.task.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentTaskListBinding
import com.example.todolist.features.task.view.component.TaskListAdapter
import com.example.todolist.features.task.view_model.common.TaskSharedViewModel
import com.example.todolist.features.task.view_model.common.TaskSharedViewModelFactory
import com.example.todolist.features.task.view_model.task_list.TaskListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {

    private val sharedViewModel: TaskSharedViewModel by activityViewModels()

    private val viewModel by viewModels<TaskListViewModel> {
        TaskSharedViewModelFactory(sharedViewModel)
    }

    private lateinit var viewBinding: FragmentTaskListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init view state
        viewBinding.recyclerView.adapter = TaskListAdapter {
            sharedViewModel.selectedTask = it
            navigateToTaskDetailFragment()
        }

        (viewBinding.recyclerView.adapter as TaskListAdapter).taskList = viewModel.taskList

        viewBinding.hintView.isVisible = viewModel.emptyTaskHint

        // add listener
        viewBinding.addButton.setOnClickListener {
            sharedViewModel.selectedTask = null
            navigateToTaskDetailFragment()
        }

    }

    private fun navigateToTaskDetailFragment() {
        findNavController().navigate(TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment())
    }
}