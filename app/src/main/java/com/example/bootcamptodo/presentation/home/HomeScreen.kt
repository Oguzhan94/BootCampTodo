package com.example.bootcamptodo.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bootcamptodo.presentation.navigation.Screen
import com.example.bootcamptodo.presentation.theme.ButtonColor
import com.example.bootcamptodo.presentation.theme.ToDoCompletedColor
import com.example.bootcamptodo.presentation.theme.ToDoPendingColor
import com.example.bootcamptodo.R

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val todos = viewModel.todos.collectAsStateWithLifecycle().value
    val fabOnClick = remember { {  navController.navigate(Screen.AddToDo()) } }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabOnClick,
                containerColor = ButtonColor,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            Text(
                text = "TO DO LIST",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Spacer(Modifier.height(40.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(
                    count = todos.size,
                    key = { index ->
                        todos[index].id!!
                    }
                ) { index ->
                    Card(
                        modifier = Modifier
                            .clickable {
                                todos[index].id?.let { navController.navigate(Screen.Detail(it)) }
                            }
                            .fillMaxWidth()
                            .height(150.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (todos[index].isCompleted) ToDoCompletedColor else ToDoPendingColor
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text =  todos[index].title.uppercase(),
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(Modifier.width(15.dp))
                                if (todos[index].isCompleted) {
                                    Icon(
                                        imageVector = Icons.Outlined.Check,
                                        contentDescription = "",
                                        tint = Color.White
                                    )

                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.clock),
                                        contentDescription = "",
                                        tint = Color.White
                                    )
                                }
                                Spacer(Modifier.width(1.dp))
                            }
                            Spacer(Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 25.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = todos[index].description,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    lineHeight = 25.sp
                                )
                            }

                        }

                    }
                    Spacer(Modifier.height(20.dp))
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)

}