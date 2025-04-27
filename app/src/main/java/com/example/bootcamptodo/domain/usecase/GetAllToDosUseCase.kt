package com.example.bootcamptodo.domain.usecase

import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllToDosUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(): Flow<List<ToDo>> {
        return toDoRepository.getAllToDos()
    }

}