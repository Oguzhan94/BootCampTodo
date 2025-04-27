package com.example.bootcamptodo.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.usecase.DeleteToDoUseCase
import com.example.bootcamptodo.domain.usecase.GetToDoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getToDoByIdUseCase: GetToDoByIdUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val id = savedStateHandle.get<Int>("id") ?: -1
    private val _toDo = MutableStateFlow<ToDo?>(null)
    val toDo : StateFlow<ToDo?>
        get() = _toDo.asStateFlow()


    init {
        getToDoById(id)
    }

    private fun getToDoById(id: Int) {
        viewModelScope.launch {
            getToDoByIdUseCase(id)

                .collect { toDo ->
                    _toDo.value = toDo
                }
        }
    }

    fun isEditMode() : Boolean{
        return id != -1
    }

     fun deleteToDo() {
        viewModelScope.launch {
            _toDo.value?.let { deleteToDoUseCase(it) }
        }

    }

}