package com.example.univcommcompose.data.remote.dto

import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.Question
import com.example.univcommcompose.domain.model.User

data class QuestionDto(
    val id: Int,
    val question: String,
    val user: User,
    val user_id: Int
)


fun QuestionDto.toQuestion(): Question {
    return Question(
        id = id,
        question = question,
        user_id = user_id,
        user = user
    )
}
