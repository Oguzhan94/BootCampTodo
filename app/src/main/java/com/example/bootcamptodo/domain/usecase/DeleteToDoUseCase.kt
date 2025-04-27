package com.example.bootcamptodo.domain.usecase

import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    suspend operator fun invoke(toDo: ToDo) {
        toDoRepository.deleteToDo(toDo)
    }
}