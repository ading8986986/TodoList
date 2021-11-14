package com.example.todolist.common.di

import android.app.Application
import androidx.room.Room
import com.example.todolist.features.task.data.data_source.TaskDao
import com.example.todolist.features.task.data.data_source.TaskDatabase
import com.example.todolist.features.task.data.respository.TaskRepositoryImpl
import com.example.todolist.features.task.domain.repository.TaskRepository
import com.example.todolist.features.task.domain.use_cases.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATA_BASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDatabase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(taskDatabase.taskDao)
    }

    @Provides
    @Singleton
    fun provideGetTaskUseCase(taskRepository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(taskRepository)
    }
}