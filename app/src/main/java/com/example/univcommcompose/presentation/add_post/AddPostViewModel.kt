package com.example.univcommcompose.presentation.add_post

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.common.UiEvent
import com.example.univcommcompose.data.remote.dto.toPost
import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.PostBody
import com.example.univcommcompose.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private @Named("auth_token") val token: String
) : ViewModel() {
    private val _state = mutableStateOf(AddPostState())
    val state: State<AddPostState> = _state

    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
//        checkAuth()
    }


    fun onEvent(event: AddPostEvent) {
        when (event) {
            is AddPostEvent.OnTitleChange -> {
                title = event.title
            }
            is AddPostEvent.OnContentChange -> {
                content = event.content
            }
            is AddPostEvent.OnAddClick -> {
                viewModelScope.launch {
                    addPost(title, content).onEach {
                        when (it) {
                            is Resource.Success -> {
                                _state.value = AddPostState(add_post_response = it.data)
                            }
                            is Resource.Error -> {
                                _state.value =
                                    AddPostState(
                                        error = it.message ?: "An unexpected error occurred"
                                    )
                            }
                            is Resource.Loading -> {
                                _state.value = AddPostState(isLoading = true)
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

    private fun addPost(title: String, content: String): Flow<Resource<Post>> = flow {
        try {
            emit(Resource.Loading())
            val addPostResponse =
                postRepository.addPost(
                    token = token,
                    postBody = PostBody(title = title, content = content)
                )
                    .toPost()
            emit(Resource.Success(addPostResponse))
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