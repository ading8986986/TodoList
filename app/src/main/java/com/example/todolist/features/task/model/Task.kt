package com.example.todolist.features.task.model

import java.util.*

data class Task(
    var title: String = "",
    var description: String = "",
    var date: Date = Date(),
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (title != other.title) return false
        if (description != other.description) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}


