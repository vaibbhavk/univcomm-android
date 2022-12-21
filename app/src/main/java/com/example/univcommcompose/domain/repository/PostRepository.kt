package com.example.univcommcompose.domain.repository

import com.example.univcommcompose.data.remote.dto.PostDto
import com.example.univcommcompose.domain.model.PostBody

interface PostRepository {

    suspend fun getPosts(token: String): List<PostDto>

    suspend fun addPost(token: String, postBody: PostBody): PostDto
}