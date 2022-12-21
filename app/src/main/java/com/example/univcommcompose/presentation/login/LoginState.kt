package com.example.univcommcompose.presentation.login

import com.example.univcommcompose.domain.model.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val login_response: LoginResponse? = null,
    val error: String = ""
)