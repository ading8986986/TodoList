package com.example.todolist.features.task.di

import com.example.todolist.features.task.data.data_source.TaskDao
import com.example.todolist.features.task.data.data_source.TaskDatabase
import com.example.todolist.features.task.data.respository.TaskRepositoryImpl
import com.example.todolist.features.task.domain.repository.TaskRepository
import com.example.todolist.features.task.domain.use_cases.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class TaskModule {

}

