package com.example.bootcamptodo.di

import com.example.bootcamptodo.data.repository.TodoRepositoryImpl
import com.example.bootcamptodo.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindToDoRepository(
        toDoRepositoryImpl: TodoRepositoryImpl
    ): ToDoRepository

}
