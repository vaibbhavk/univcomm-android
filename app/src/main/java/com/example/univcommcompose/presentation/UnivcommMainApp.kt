package com.example.univcommcompose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.univcommcompose.presentation.Screen
import com.example.univcommcompose.presentation.layouts.DefaultLayout
import com.example.univcommcompose.presentation.layouts.ScaffoldLayout

@Composable
fun UnivcommMainApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        composable(route = Screen.WelcomeScreen.route) {
            DefaultLayout(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            DefaultLayout(navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            DefaultLayout(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            ScaffoldLayout(navController)
        }
        composable(route = Screen.NotificationScreen.route) {
            ScaffoldLayout(navController)
        }
        composable(route = Screen.EditProfileScreen.route) {
            ScaffoldLayout(navController)
        }
        composable(route = Screen.ProfileSummaryScreen.route) {
            ScaffoldLayout(navController)
        }
        composable(route = Screen.AddPostScreen.route) {
            ScaffoldLayout(navController)
        }
    }
}