package com.example.univcommcompose.presentation.layouts

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.presentation.home.HomeState
import com.example.univcommcompose.presentation.login.LoginEvent
import com.example.univcommcompose.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScaffoldLayoutViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel(

) {
    private val _state = mutableStateOf(ScaffoldLayoutState())
    val state: State<ScaffoldLayoutState> = _state

    init {

    }

    fun onEvent(event: ScaffoldLayoutEvent) {
        when (event) {
            is ScaffoldLayoutEvent.OnLogoutClick -> {
                viewModelScope.launch {
                    sharedPreferences.edit().remove("token").apply()
                }
            }
        }
    }


}



