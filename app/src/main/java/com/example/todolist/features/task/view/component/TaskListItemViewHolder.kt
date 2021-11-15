package com.example.todolist.features.task.view.component

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.common.util.TimeFormat
import com.example.todolist.databinding.TaskListItemViewHolderBinding
import com.example.todolist.features.task.model.Task

class TaskListItemViewHolder(val viewbinding: TaskListItemViewHolderBinding) :
    RecyclerView.ViewHolder(viewbinding.root) {

    fun bindData(task: Task, onTaskClickListener: ((task: Task) -> Unit)? = null) {
        viewbinding.apply {
            title.text = task.title

            content.text = task.description
            content.isVisible = task.description.isNotEmpty()

            date.text = TimeFormat.getDateFormattedString(task.date)
            itemView.contentDescription =
                "Task of $title, created on ${TimeFormat.getDateFormattedString(task.date)}"

            onTaskClickListener?.let {
                itemView.setOnClickListener {
                    it(task)
                }
            }
        }
    }
}