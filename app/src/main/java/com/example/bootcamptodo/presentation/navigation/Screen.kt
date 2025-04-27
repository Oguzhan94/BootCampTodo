package com.example.bootcamptodo.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen{

    @Serializable
    data object Home : Screen

    @Serializable
    data class AddToDo(val id : Int? = null) : Screen

    @Serializable
    data class Detail(val id: Int) : Screen
}