package com.example.univcommcompose.presentation.register

import com.example.univcommcompose.domain.model.RegisterResponse

data class RegisterState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val register_response: RegisterResponse? = null,
    val error: String = ""
)