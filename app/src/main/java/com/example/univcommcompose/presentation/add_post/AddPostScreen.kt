package com.example.univcommcompose.presentation.add_post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.univcommcompose.presentation.Screen
import com.example.univcommcompose.presentation.login.LoginEvent
import com.example.univcommcompose.presentation.login.LoginViewModel


@Composable
fun AddPostScreen(
    navController: NavController, addPostViewModel: AddPostViewModel = hiltViewModel()
) {
    val state = addPostViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Start a thread", fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(value = addPostViewModel.title, onValueChange = {
                addPostViewModel.onEvent(AddPostEvent.OnTitleChange(it))
            }, label = { Text("Title") }, singleLine = true, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = addPostViewModel.content,
                onValueChange = {
                    addPostViewModel.onEvent(AddPostEvent.OnContentChange(it))
                },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    addPostViewModel.onEvent(AddPostEvent.OnAddClick)
                }) {
                    Text(text = "Add", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Button(onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Text(text = "Cancel", fontSize = 24.sp)
                }
            }

        }

        if (state.add_post_response != null) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }

                launchSingleTop = true
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
            )
        }

        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }

}

