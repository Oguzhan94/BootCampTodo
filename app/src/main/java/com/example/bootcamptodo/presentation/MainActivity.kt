package com.example.bootcamptodo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bootcamptodo.presentation.navigation.NavigationGraph
import com.example.bootcamptodo.presentation.navigation.Screen
import com.example.bootcamptodo.presentation.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                NavigationGraph(
                    startDestination = Screen.Home
                )
            }
        }
    }
}