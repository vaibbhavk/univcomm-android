package com.example.univcommcompose.domain.model

data class Post(
    val id: Int,
    val content: String,
    val title: String,
    val user_id: Int,
    val user: User
)
