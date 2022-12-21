package com.example.univcommcompose.data.remote

import com.example.univcommcompose.data.remote.dto.LoginResponseDto
import com.example.univcommcompose.data.remote.dto.PostDto
import com.example.univcommcompose.data.remote.dto.QuestionDto
import com.example.univcommcompose.data.remote.dto.RegisterResponseDto
import com.example.univcommcompose.domain.model.LoginBody
import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.PostBody
import com.example.univcommcompose.domain.model.RegisterBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UnivcommApi {

    @POST("v1/auth/login")
    suspend fun login(@Body loginBody: LoginBody): LoginResponseDto

    @POST("v1/auth/register")
    suspend fun register(@Body registerBody: RegisterBody): RegisterResponseDto

    @GET("v1/user/threads")
    suspend fun getPosts(@Header("token") token: String): List<PostDto>

    @POST("v1/user/post")
    suspend fun addPost(@Header("token") token: String, @Body postBody: PostBody): PostDto

    @GET("v1/user/queries")
    suspend fun getQuestions(@Header("token") token: String): List<QuestionDto>


}