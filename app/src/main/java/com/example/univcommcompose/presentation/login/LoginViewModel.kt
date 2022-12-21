package com.example.univcommcompose.presentation.login

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.data.remote.dto.toLoginResponse
import com.example.univcommcompose.domain.model.LoginBody
import com.example.univcommcompose.domain.model.LoginResponse
import com.example.univcommcompose.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences,
    private val context: Context
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
//        checkAuth()
    }


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                email = event.email
            }
            is LoginEvent.OnPasswordChange -> {
                password = event.password
            }
            is LoginEvent.OnLoginClick -> {
                viewModelScope.launch {
                    if (email.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Please enter your email address"
                            )
                        )
                        return@launch
                    }
                    if (password.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Please enter your password"
                            )
                        )
                        return@launch
                    }

                    login(email, password).onEach {
                        when (it) {
                            is Resource.Success -> {
                                sharedPreferences.edit()
                                    .putString("token", it.data!!.access_token).apply()

                                _state.value = LoginState(login_response = it.data)
                            }
                            is Resource.Error -> {
                                _state.value =
                                    LoginState(error = it.message ?: "An unexpected error occurred")
                            }
                            is Resource.Loading -> {
                                _state.value = LoginState(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)

                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun login(email: String, password: String): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val loginResponse =
                userRepository.login(loginBody = LoginBody(email, password)).toLoginResponse()
            emit(Resource.Success(loginResponse))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "An unexpected error occurred.",
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection."))
        }
    }

//    private fun checkAuth() {
//
//        val sharedPreferences =
//            context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)
//
//        val token = sharedPreferences.getString("token", "")
//
//        Log.i("Login", token!!)
//
//        if (token.isNotBlank()) {
//            _state.value = LoginState(isAuthenticated = true)
//        }
//
//
//    }
}