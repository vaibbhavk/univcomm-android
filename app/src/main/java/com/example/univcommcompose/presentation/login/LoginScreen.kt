package com.example.univcommcompose.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.presentation.Screen
import com.example.univcommcompose.presentation.login.LoginEvent
import com.example.univcommcompose.presentation.login.LoginViewModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state = loginViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Sign in", fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "or")
                TextButton(onClick = {
                    navController.navigate(Screen.RegisterScreen.route) {
                        popUpTo(Screen.WelcomeScreen.route)
                    }
                }) {
                    Text(text = "Sign up")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextField(value = loginViewModel.email, onValueChange = {
                loginViewModel.onEvent(LoginEvent.OnEmailChange(it))
            }, label = { Text("Email") }, singleLine = true, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = loginViewModel.password,
                onValueChange = {
                    loginViewModel.onEvent(LoginEvent.OnPasswordChange(it))
                },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                loginViewModel.onEvent(LoginEvent.OnLoginClick)
            }) {
                Text(text = "Sign in", fontSize = 24.sp)
            }

        }

        if (state.login_response != null) {
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

