package com.example.univcommcompose.data.remote.dto

import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.User

data class PostDto(
    val content: String,
    val id: Int,
    val title: String,
    val user: User,
    val user_id: Int
)

fun PostDto.toPost(): Post {
    return Post(
        id = id,
        content = content,
        title = title,
        user_id = user_id,
        user = user
    )
}