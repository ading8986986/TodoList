package com.example.todolist.common.core

sealed class UseCaseResponse<out T>() {
    data class Success<T>(val data: T) : UseCaseResponse<T>()
    object Loading : UseCaseResponse<Nothing>()
    data class Error(val message: String) : UseCaseResponse<Nothing>()
}

