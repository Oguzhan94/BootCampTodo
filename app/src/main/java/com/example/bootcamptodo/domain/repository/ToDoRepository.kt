package com.example.bootcamptodo.domain.repository

import com.example.bootcamptodo.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getAllToDos(): Flow<List<ToDo>>
    fun getToDoById(id: Int): Flow<ToDo>
    suspend fun insertToDo(toDo: ToDo)
    suspend fun updateToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)

}