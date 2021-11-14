package com.example.todolist.common.core

sealed class RepositoryResponse<out T> {
    data class Success<T>(val data: T) : RepositoryResponse<T>()
    data class Error(val message: String) : RepositoryResponse<Nothing>()

    fun isSuccess(): Boolean {
        return this is Success
    }
}
