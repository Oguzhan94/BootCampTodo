package com.example.bootcamptodo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bootcamptodo.presentation.add.AddToDoScreen
import com.example.bootcamptodo.presentation.detail.DetailScreen
import com.example.bootcamptodo.presentation.home.HomeScreen

@Composable
fun NavigationGraph(
    startDestination : Screen,
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home>{
            HomeScreen(navController = navController)
        }

        composable<Screen.AddToDo>{
            AddToDoScreen(navController = navController)
        }
        composable<Screen.Detail> {
            DetailScreen(navController = navController)
        }

    }
}