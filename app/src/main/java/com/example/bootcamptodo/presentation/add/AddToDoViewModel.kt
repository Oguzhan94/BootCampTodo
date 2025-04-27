package com.example.bootcamptodo.presentation.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamptodo.domain.model.ToDo
import com.example.bootcamptodo.domain.usecase.GetToDoByIdUseCase
import com.example.bootcamptodo.domain.usecase.InsertToDoUseCase
import com.example.bootcamptodo.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val insertToDoUseCase: InsertToDoUseCase,
    private val getToDoByIdUseCase: GetToDoByIdUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel()  {

    private val _toDo = MutableStateFlow<ToDo?>(null)
    val toDo : StateFlow<ToDo?>
        get() = _toDo.asStateFlow()

    private val id = savedStateHandle.get<Int>("id")

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var isCompleted by mutableStateOf(false)
        private set

    init {
        if (id != null){
            getToDoById(id)
        }
    }

    fun onIsCompletedChanged(newIsCompleted: Boolean) {
        isCompleted = newIsCompleted
    }

    fun onTitleChanged(newTitle: String) {
        title = newTitle
    }

    fun onDescriptionChanged(newDesc: String) {
        description = newDesc
    }

    fun isEditMode() : Boolean{
        return id != null
    }

    fun addToDo(){
        viewModelScope.launch {
            val toDo = ToDo(
                title = title,
                description = description,
                isCompleted = false
            )
            insertToDoUseCase(toDo)
        }
    }

    private fun getToDoById(id: Int) {
        viewModelScope.launch {
            getToDoByIdUseCase(id)
                .collect { toDo ->
                    _toDo.value = toDo
                    _toDo.value?.let {
                        onTitleChanged(it.title)
                        onDescriptionChanged(it.description)
                        onIsCompletedChanged(it.isCompleted)
                    }
                }
        }
    }

    fun updateToDo(){
       viewModelScope.launch {
           val toDo = ToDo(
               id = _toDo.value?.id,
               title = title,
               description = description,
               isCompleted = isCompleted
           )
           updateToDoUseCase(toDo)
       }
    }
}