package com.example.univcommcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.univcommcompose.presentation.home.HomeEvent
import com.example.univcommcompose.presentation.home.HomeViewModel
import com.example.univcommcompose.presentation.home.components.PostList
import com.example.univcommcompose.presentation.home.components.QuestionList

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val state = homeViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var tabIndex by remember { mutableStateOf(0) }
        val tabTitles = listOf("Threads", "Queries")

        Column {
            TabRow(selectedTabIndex = tabIndex) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            homeViewModel.onEvent(HomeEvent.OnTabChange(tabIndex))
                        },
                        text = { Text(text = title) })
                }
            }

            when (tabIndex) {
                0 -> PostList(state)
                1 -> QuestionList(state)
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
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}