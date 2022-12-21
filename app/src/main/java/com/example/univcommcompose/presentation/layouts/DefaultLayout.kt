package com.example.univcommcompose.presentation.layouts


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.univcommcompose.presentation.Screen
import com.example.univcommcompose.presentation.register.RegisterScreen
import com.example.univcommcompose.screens.*


@Composable
fun DefaultLayout(navController: NavController) {
    when (navController.currentDestination?.route) {
        Screen.LoginScreen.route -> LoginScreen(navController)
        Screen.RegisterScreen.route -> RegisterScreen(navController)
        Screen.WelcomeScreen.route -> WelcomeScreen(navController)
    }
}