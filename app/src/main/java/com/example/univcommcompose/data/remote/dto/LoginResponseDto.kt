package com.example.univcommcompose.data.remote.dto

import com.example.univcommcompose.domain.model.LoginResponse

data class LoginResponseDto(
    val access_token: String,
    val token_type: String
)

fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(access_token = access_token)
}
