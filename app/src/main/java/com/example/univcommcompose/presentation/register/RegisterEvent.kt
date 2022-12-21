package com.example.univcommcompose.presentation.register

sealed class RegisterEvent {
    data class OnUserTypeChange(val userType: String) : RegisterEvent()
    data class OnFirstNameChange(val firstName: String) : RegisterEvent()
    data class OnLastNameChange(val lastName: String) : RegisterEvent()
    data class OnEmailChange(val email: String) : RegisterEvent()
    data class OnPasswordChange(val password: String) : RegisterEvent()
    data class OnConfirmPasswordChange(val confirmPassword: String) : RegisterEvent()
    data class OnRollNoChange(val rollNo: String) : RegisterEvent()
    object OnRegisterClick : RegisterEvent()
}