package com.example.univcommcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.univcommcompose.presentation.Screen
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = "Welcome to the college community!",
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                Text(text = "Sign in", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { navController.navigate(Screen.RegisterScreen.route) }) {
                Text(text = "Sign up", fontSize = 24.sp)
            }

//            Spacer(modifier = Modifier.height(32.dp))
//
//            Row(modifier = Modifier.fillMaxWidth()) {
//                var count by remember { mutableStateOf(0) }
//
//                Card(
//                    onClick = { count++ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .height(100.dp)
//                ) {
//                    Box(Modifier.fillMaxSize()) {
//                        Text("Count: $count", Modifier.align(Alignment.Center))
//                    }
//                }
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .height(100.dp)
//                ) {
//                    Box(Modifier.fillMaxSize()) {
//                        Text("Card 2", Modifier.align(Alignment.Center))
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Row(modifier = Modifier.fillMaxWidth()) {
//                var count by remember { mutableStateOf(0) }
//
//                Card(
//                    onClick = { count++ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .height(100.dp)
//                ) {
//                    Box(Modifier.fillMaxSize()) {
//                        Text("Count: $count", Modifier.align(Alignment.Center))
//                    }
//                }
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                        .height(100.dp)
//                ) {
//                    Box(Modifier.fillMaxSize()) {
//                        Text("Card 2", Modifier.align(Alignment.Center))
//                    }
//                }
//            }


        }
    }
}