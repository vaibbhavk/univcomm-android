package com.example.univcommcompose.data.repository

import com.example.univcommcompose.data.remote.UnivcommApi
import com.example.univcommcompose.data.remote.dto.LoginResponseDto
import com.example.univcommcompose.data.remote.dto.PostDto
import com.example.univcommcompose.data.remote.dto.RegisterResponseDto
import com.example.univcommcompose.domain.model.LoginBody
import com.example.univcommcompose.domain.model.RegisterBody
import com.example.univcommcompose.domain.repository.PostRepository
import com.example.univcommcompose.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UnivcommApi) : UserRepository {
    override suspend fun login(loginBody: LoginBody): LoginResponseDto {
        return api.login(loginBody)
    }

    override suspend fun register(registerBody: RegisterBody): RegisterResponseDto {
        return api.register(registerBody)
    }
}