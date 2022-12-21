package com.example.univcommcompose.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val state = registerViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = "Sign up",
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "or")
                TextButton(onClick = {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.WelcomeScreen.route)
                    }
                }) {
                    Text(text = "Sign in")
                }
            }


            Spacer(modifier = Modifier.height(32.dp))


            Text(text = "Your role in the university?")
            val radioOptions = listOf("Student", "Faculty", "Alumni")
            val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
            Column(Modifier.selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = registerViewModel.firstName,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnFirstNameChange(it))
                },
                label = { Text("First Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = registerViewModel.lastName,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnLastNameChange(it))
                },
                label = { Text("Last Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )


            Spacer(modifier = Modifier.height(32.dp))


            TextField(
                value = registerViewModel.email,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnEmailChange(it))
                },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = registerViewModel.password,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnPasswordChange(it))
                },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
            )


            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = registerViewModel.confirmPassword,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnConfirmPasswordChange(it))
                },
                label = { Text("Confirm Password") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = registerViewModel.rollNo,
                onValueChange = {
                    registerViewModel.onEvent(RegisterEvent.OnRollNoChange(it))
                },
                label = { Text("Roll No./Faculty Id") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(onClick = {
                registerViewModel.onEvent(RegisterEvent.OnRegisterClick)
            }) {
                Text(text = "Sign up", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

        }


        if (state.register_response != null) {
//            navController.popBackStack(Screen.WelcomeScreen.route, inclusive = true)
            navController.popBackStack()
            navController.navigate(Screen.LoginScreen.route)
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
