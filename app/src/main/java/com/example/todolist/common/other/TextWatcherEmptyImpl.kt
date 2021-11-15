package com.example.todolist.common.other
import android.text.Editable
import android.text.TextWatcher

open class TextWatcherEmptyImpl : TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}