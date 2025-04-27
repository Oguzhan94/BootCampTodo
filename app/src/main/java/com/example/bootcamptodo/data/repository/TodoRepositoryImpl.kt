package com.example.bootcamptodo.data.repository

import com.example.bootcamptodo.data.local.ToDoDao
import com.example.bootcamptodo.data.toDomainOrNull
import com.example.bootcamptodo.data.toEntity
import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val toDoDao: ToDoDao) : ToDoRepository {

    override fun getAllToDos(): Flow<List<ToDo>> {
        return toDoDao.getAllToDos()
            .map { toDoEntities ->
                toDoEntities.mapNotNull { it.toDomainOrNull() }
            }
    }

    override fun getToDoById(id: Int): Flow<ToDo> {
        return toDoDao.getToDoById(id)
            .map { it.toDomainOrNull() }
            .filterNotNull()
    }

    override suspend fun insertToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo.toEntity())
    }

    override suspend fun updateToDo(toDo: ToDo) {
        toDoDao.updateToDo(toDo.toEntity())
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        toDoDao.deleteToDo(toDo.toEntity())
    }

}