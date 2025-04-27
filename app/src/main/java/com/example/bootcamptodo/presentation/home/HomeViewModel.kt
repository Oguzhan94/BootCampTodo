package com.example.bootcamptodo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.usecase.GetAllToDosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllToDosUseCase: GetAllToDosUseCase
) : ViewModel()  {

    private val _todos = MutableStateFlow<List<ToDo>>(emptyList())
    val todos: StateFlow<List<ToDo>>
        get() = _todos.asStateFlow()

    init {
        getAllToDos()
    }

    private fun getAllToDos(){
        viewModelScope.launch {
            getAllToDosUseCase()
                .collect { toDoList ->
                    _todos.value = toDoList
                }
        }
    }

}