package com.example.univcommcompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.domain.use_case.get_posts.GetPostsUseCase
import com.example.univcommcompose.domain.use_case.get_questions.GetQuestionsUseCase
import com.example.univcommcompose.presentation.register.RegisterEvent
import com.example.univcommcompose.presentation.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getPosts()
    }

    private fun getPosts() {
        getPostsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(posts = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getQuestions() {
        getQuestionsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(questions = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HomeState(error = it.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnTabChange -> {
                viewModelScope.launch {
                    when (event.index) {
                        0 -> getPosts()
                        1 -> getQuestions()
                    }
                }
            }
        }
    }

}