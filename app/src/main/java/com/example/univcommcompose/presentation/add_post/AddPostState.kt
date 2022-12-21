package com.example.univcommcompose.presentation.add_post

import com.example.univcommcompose.domain.model.Post

data class AddPostState(
    val isLoading: Boolean = false,
    val add_post_response: Post? = null,
    val error: String = ""
)