package com.example.univcommcompose.domain.model

data class RegisterResponse(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val roll_no: String,
    val user_type: Int,
    val is_active: Int
)
