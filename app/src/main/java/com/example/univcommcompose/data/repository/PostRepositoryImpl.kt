package com.example.univcommcompose.data.repository

import com.example.univcommcompose.data.remote.UnivcommApi
import com.example.univcommcompose.data.remote.dto.PostDto
import com.example.univcommcompose.domain.model.PostBody
import com.example.univcommcompose.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val api: UnivcommApi) : PostRepository {
    override suspend fun getPosts(token: String): List<PostDto> {
        return api.getPosts(token = token)
    }

    override suspend fun addPost(token: String, postBody: PostBody): PostDto {
        return api.addPost(token = token, postBody = postBody)
    }
}