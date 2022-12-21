package com.example.univcommcompose.domain.repository

import com.example.univcommcompose.data.remote.dto.LoginResponseDto
import com.example.univcommcompose.data.remote.dto.RegisterResponseDto
import com.example.univcommcompose.domain.model.LoginBody
import com.example.univcommcompose.domain.model.RegisterBody

interface UserRepository {
    suspend fun login(loginBody: LoginBody): LoginResponseDto
    suspend fun register(registerBody: RegisterBody): RegisterResponseDto
}