package com.example.univcommcompose.presentation.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.univcommcompose.R
import com.example.univcommcompose.presentation.Screen
import com.example.univcommcompose.presentation.add_post.AddPostScreen
import com.example.univcommcompose.presentation.login.LoginEvent
import com.example.univcommcompose.screens.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout(
    navController: NavController,
    scaffoldLayoutViewModel: ScaffoldLayoutViewModel = hiltViewModel()
) {


    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Univcomm") },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }

                    var expanded by remember { mutableStateOf(false) }

                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Menu Bar"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {

                            DropdownMenuItem(
                                text = { Text("My Profile") },
                                onClick = {
                                    navController.navigate(Screen.ProfileSummaryScreen.route) {
                                        popUpTo(Screen.ProfileSummaryScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Face,
                                        contentDescription = null
                                    )
                                })
                            MenuDefaults.Divider()
                            DropdownMenuItem(
                                text = { Text("Logout") },
                                onClick = {
                                    scaffoldLayoutViewModel.onEvent(ScaffoldLayoutEvent.OnLogoutClick)
                                    navController.navigate(Screen.WelcomeScreen.route) {
                                        popUpTo(Screen.HomeScreen.route) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.ExitToApp,
                                        contentDescription = null
                                    )
                                }
                            )

                        }
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                icons = {
                    IconButton(onClick = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) {
                                inclusive = true
                            }
                        }


                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.NotificationScreen.route) {
                            popUpTo(Screen.NotificationScreen.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                        )
                    }

                },
                floatingActionButton = {
                    var expanded by remember { mutableStateOf(false) }

                    FloatingActionButton(
                        onClick = { expanded = true },
                    ) {
                        Icon(Icons.Filled.Add, "Add")
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Ask") },
                                onClick = {
                                    navController.navigate(Screen.AddPostScreen.route) {
                                        popUpTo(Screen.ProfileSummaryScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                                leadingIcon = {
                                    Icon(
                                        painterResource(id = R.drawable.ic_outline_live_help_24),
                                        contentDescription = null
                                    )
                                })
                            MenuDefaults.Divider()
                            DropdownMenuItem(
                                text = { Text("Post") },
                                onClick = {
                                    scaffoldLayoutViewModel.onEvent(ScaffoldLayoutEvent.OnLogoutClick)
                                    navController.navigate(Screen.AddPostScreen.route) {
                                        launchSingleTop = true
                                    }
                                },
                                leadingIcon = {
                                    Icon(
                                        painterResource(id = R.drawable.ic_outline_edit_24),
                                        contentDescription = null
                                    )
                                }
                            )

                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (navController.currentDestination?.route) {
                Screen.HomeScreen.route -> HomeScreen(navController)
                Screen.NotificationScreen.route -> NotificationScreen(navController)
                Screen.ProfileSummaryScreen.route -> ProfileSummaryScreen(navController)
                Screen.EditProfileScreen.route -> EditProfileScreen(navController)
                Screen.AddPostScreen.route -> AddPostScreen(navController)
            }
        }
    }
}