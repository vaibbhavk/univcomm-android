package com.example.univcommcompose.domain.model

data class Question(
    val id: Int,
    val question: String,
    val user_id: Int,
    val user: User
)