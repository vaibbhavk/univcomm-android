package com.example.univcommcompose.presentation

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcome_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object NotificationScreen : Screen("notification_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object ProfileSummaryScreen : Screen("profile_summary_screen")
    object AddPostScreen : Screen("add_post_screen")

//    fun withArgs(vararg args: String): String {
//        return buildString {
//            append(route)
//            args.forEach { arg ->
//                append("/$arg")
//            }
//        }
//    }
}