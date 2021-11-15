package com.example.todolist.common.util

import java.text.SimpleDateFormat
import java.util.*

object TimeFormat {

    fun getDateFormattedString(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .format(date)
    }
    //Convert Date to Calendar
    fun dateToCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    //Convert Calendar to Date
    fun calendarToDate(year: Int, month: Int, day: Int): Date {
        val cal = Calendar.getInstance()
        cal.clear()

        cal[Calendar.YEAR] = year
        cal[Calendar.MONTH] = month
        cal[Calendar.DATE] = day
        return cal.time
    }


}