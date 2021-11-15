package com.example.todolist.features.task.view.ui

import android.app.DatePickerDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.common.other.TextWatcherEmptyImpl
import com.example.todolist.common.util.TimeFormat
import com.example.todolist.databinding.FragmentTaskDetailBinding
import com.example.todolist.features.task.view_model.common.TaskSharedViewModel
import com.example.todolist.features.task.view_model.common.TaskSharedViewModelFactory
import com.example.todolist.features.task.view_model.task_detail.TaskDetailViewEvent.*
import com.example.todolist.features.task.view_model.task_detail.TaskDetailViewModel
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [TaskDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentTaskDetailBinding

    private val sharedViewModel: TaskSharedViewModel by activityViewModels()


    private val viewModel by viewModels<TaskDetailViewModel> {
        TaskSharedViewModelFactory(sharedViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewmodel = viewModel
        viewBinding.toolBar.inflateMenu(R.menu.detial_tool_bar)
        addListeners()
        addObservers()
    }

    private fun addListeners() {
        viewBinding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        viewBinding.toolBar.setNavigationOnClickListener { view ->
            findNavController().popBackStack()
        }
        viewBinding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_save -> {
                    viewModel.onEvent(SaveTaskEvent)
                    true
                }
                else -> false
            }
        }
        viewBinding.dateText.setOnClickListener {
            showDatePicker()
        }

        viewBinding.titleText.addTextChangedListener(object : TextWatcherEmptyImpl() {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEvent(EditTitleEvent(s?.toString() ?: ""))
            }
        })

        viewBinding.contentText.addTextChangedListener(object : TextWatcherEmptyImpl() {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEvent(EditContentEvent(s?.toString() ?: ""))
            }
        })
    }

    private fun addObservers() {
        viewModel.popStackEvent.observe(viewLifecycleOwner) {
            try {
                val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(viewBinding.root.getWindowToken(), 0);
            } catch (e: Exception) {
            }
            findNavController().popBackStack()
        }

        viewModel.saveButtonVisibility.observe(viewLifecycleOwner) {
            viewBinding.toolBar.menu.findItem(R.id.action_save).isVisible = it
        }
        viewModel.taskDateString.observe(viewLifecycleOwner){
            viewBinding.dateText.text = it
        }
    }

    private fun showDatePicker() {
        val calendar = TimeFormat.dateToCalendar(viewModel.task.date)
        val picker = DatePickerDialog(
            context ?: return,
            { _, year, month, day ->
                viewModel.onEvent(EditDateEvent(TimeFormat.calendarToDate(year, month, day)))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        picker.show()
    }
}