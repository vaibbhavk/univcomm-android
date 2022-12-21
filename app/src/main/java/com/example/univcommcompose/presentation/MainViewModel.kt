package com.example.univcommcompose.presentation

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val context: Context
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isAuthenticated = mutableStateOf(false)
    val isAuthenticated = _isAuthenticated.value

    private val _isChecking = mutableStateOf(true)
    val isChecking = _isChecking.value

    init {
//        viewModelScope.launch {
//            checkAuth()
//        }
        viewModelScope.launch {
            delay(1000)
            _isLoading.value = false
        }
    }

    private fun checkAuth() {
        val sharedPreferences =
            context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", "")

        if (token!!.isNotBlank()) {
            _isAuthenticated.value = true
        }

        _isLoading.value = false
    }
}