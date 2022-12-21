package com.example.univcommcompose.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.data.remote.dto.toRegisterResponse
import com.example.univcommcompose.domain.model.RegisterBody
import com.example.univcommcompose.domain.model.RegisterResponse
import com.example.univcommcompose.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    var userType by mutableStateOf("Student")
        private set

    var userTypeInt by mutableStateOf(0)
        private set

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var rollNo by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
//        checkAuth()
    }


    fun onEvent(event: RegisterEvent) {
        when (event) {

            is RegisterEvent.OnUserTypeChange -> {
                userType = event.userType
            }
            is RegisterEvent.OnFirstNameChange -> {
                firstName = event.firstName
            }
            is RegisterEvent.OnLastNameChange -> {
                lastName = event.lastName
            }
            is RegisterEvent.OnEmailChange -> {
                email = event.email
            }
            is RegisterEvent.OnPasswordChange -> {
                password = event.password
            }
            is RegisterEvent.OnConfirmPasswordChange -> {
                confirmPassword = event.confirmPassword
            }
            is RegisterEvent.OnRollNoChange -> {
                rollNo = event.rollNo
            }
            is RegisterEvent.OnRegisterClick -> {
                viewModelScope.launch {
                    if (firstName.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Please enter your first name"
                            )
                        )
                        return@launch
                    }
                    if (lastName.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Please enter your last name"
                            )
                        )
                        return@launch
                    }
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
                    if (confirmPassword.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Please enter your password"
                            )
                        )
                        return@launch
                    }


                    when (userType) {
                        "Student" -> {
                            userTypeInt = 0
                        }
                        "Faculty" -> {
                            userTypeInt = 1
                        }
                        "Alumni" -> {
                            userTypeInt = 2
                        }
                    }

                    register(
                        userTypeInt,
                        firstName,
                        lastName,
                        email,
                        password,
                        confirmPassword,
                        rollNo
                    ).onEach {
                        when (it) {
                            is Resource.Success -> {
                                _state.value = RegisterState(register_response = it.data)
                            }
                            is Resource.Error -> {
                                _state.value =
                                    RegisterState(
                                        error = it.message ?: "An unexpected error occurred"
                                    )
                            }
                            is Resource.Loading -> {
                                _state.value = RegisterState(isLoading = true)
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

    fun register(
        userType: Int,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
        rollNo: String
    ): Flow<Resource<RegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            println("$userType $firstName $lastName $email $password $confirmPassword $rollNo")
            val registerResponse =
                userRepository.register(
                    registerBody = RegisterBody(
                        userType,
                        firstName,
                        lastName,
                        email,
                        password,
                        confirmPassword,
                        rollNo
                    )
                ).toRegisterResponse()
            emit(Resource.Success(registerResponse))
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


}