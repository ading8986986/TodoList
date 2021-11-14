package com.example.todolist.features.task.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
data class Task(
    val title: String,
    val description: String,
    val date: Date = Date(),
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Parcelable{
    override fun equals(other: Any?): Boolean {
        return other is Task &&
                title == other.title &&
                description == other.description &&
                date.time == other.date.time
    }
}

fun Task.getContentDescription():String{
    return "Task of $title, created on $date"
}

