package com.example.univcommcompose.domain.model

import com.example.univcommcompose.presentation.register.RegisterEvent

data class RegisterBody(
    val user_type: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val confirm_password: String,
    val roll_no: String
)