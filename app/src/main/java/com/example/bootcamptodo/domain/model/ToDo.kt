package com.example.bootcamptodo.domain.model

data class ToDo(
    val id : Int? = null,
    val title : String,
    val description : String,
    val isCompleted : Boolean,
)
