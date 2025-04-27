package com.example.bootcamptodo.domain.usecase

import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDoByIdUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(id: Int): Flow<ToDo> {
        return toDoRepository.getToDoById(id)
    }
}