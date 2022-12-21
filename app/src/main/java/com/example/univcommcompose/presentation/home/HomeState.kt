package com.example.univcommcompose.presentation.home

import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.Question

data class HomeState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val questions: List<Question> = emptyList(),
    val error: String = ""
)
