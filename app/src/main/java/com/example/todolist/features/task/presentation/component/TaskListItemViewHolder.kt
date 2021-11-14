package com.example.todolist.features.task.presentation.component

import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.common.util.TimeFormat
import com.example.todolist.databinding.TaskListItemViewHolderBinding
import com.example.todolist.features.task.domain.model.Task
import com.example.todolist.features.task.domain.model.getContentDescription

class TaskListItemViewHolder(val viewbinding: TaskListItemViewHolderBinding) :
    RecyclerView.ViewHolder(viewbinding.root) {

    fun bindData(task: Task, onTaskClickListener: ((task: Task) -> Unit)? = null) {
        viewbinding.apply {
            title.text = task.title
            content.text = task.description
            date.text = TimeFormat.getDateFormattedString(task.date)
            itemView.contentDescription = task.getContentDescription()

            onTaskClickListener?.let {
                itemView.setOnClickListener {
                    it(task)
                }
            }
        }
    }

}