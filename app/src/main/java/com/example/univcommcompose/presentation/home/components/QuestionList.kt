package com.example.univcommcompose.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.univcommcompose.presentation.home.HomeState

@Composable
fun QuestionList(state: HomeState) {
    Box(modifier = Modifier.padding(8.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.questions) { question ->
                QuestionListItem(question = question)
            }
        }
    }
}