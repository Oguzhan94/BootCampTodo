package com.example.bootcamptodo.presentation.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bootcamptodo.presentation.theme.ButtonColor
import com.example.bootcamptodo.presentation.theme.ToDoCompletedColor
import com.example.bootcamptodo.presentation.theme.ToDoPendingColor

@Composable
fun AddToDoScreen(
    navController: NavController
){
    val viewModel = hiltViewModel<AddToDoViewModel>()
    val todo = viewModel.toDo.collectAsStateWithLifecycle().value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (viewModel.isEditMode()){
                    Box(
                        modifier = Modifier.weight(1f)
                    ){
                        Text(
                            text = if (viewModel.isCompleted) "Done" else "Mark as done"
                        )
                    }

                    todo?.let {
                        Switch(
                            checked = viewModel.isCompleted,
                            onCheckedChange = {
                                viewModel.onIsCompletedChanged(it)
                            },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = ToDoCompletedColor,
                                uncheckedTrackColor = ToDoPendingColor,
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White
                            ),
                            modifier = Modifier
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { navController.navigateUp() },
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(34.dp)
                    )
                }

            }
            Spacer(Modifier.height(40.dp))
            OutlinedTextField(
                value = viewModel.title,
                onValueChange = { viewModel.onTitleChanged(it) },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (viewModel.isEditMode()) viewModel.updateToDo() else viewModel.addToDo()
                    navController.navigateUp()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                )
            ) {
                Text(
                text = if (viewModel.isEditMode()) "Update ToDo" else "Add ToDo"
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewAddToDoScreen(){
    AddToDoScreen(rememberNavController())
}