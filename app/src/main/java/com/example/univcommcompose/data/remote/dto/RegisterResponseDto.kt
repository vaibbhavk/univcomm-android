package com.example.univcommcompose.data.remote.dto

import com.example.univcommcompose.domain.model.RegisterResponse

data class RegisterResponseDto(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val roll_no: String,
    val user_type: Int,
    val is_active: Int
)

fun RegisterResponseDto.toRegisterResponse(): RegisterResponse {
    return RegisterResponse(
        id = id,
        email = email,
        first_name = first_name,
        last_name = last_name,
        roll_no = roll_no,
        user_type = user_type,
        is_active = is_active
    )
}
