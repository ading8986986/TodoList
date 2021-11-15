package com.example.todolist.features.task.view.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskListItemViewHolderBinding
import com.example.todolist.features.task.model.Task

class TaskListAdapter(private val onTaskClickListener: ((task: Task) -> Unit)? = null) :
    RecyclerView.Adapter<TaskListItemViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var taskList: List<Task>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListItemViewHolder {
        val viewBinding = TaskListItemViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskListItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: TaskListItemViewHolder, position: Int) {
        holder.bindData(taskList[position], onTaskClickListener)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}