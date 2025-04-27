package com.example.bootcamptodo.di

import android.content.Context
import androidx.room.Room
import com.example.bootcamptodo.data.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : ToDoDatabase = Room.databaseBuilder(
        context = context,
        klass = ToDoDatabase::class.java,
        name = "todo_database"
    ).build()

    @Provides
    fun provideDao(database: ToDoDatabase) = database.toDoDao()
}