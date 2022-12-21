package com.example.univcommcompose.presentation.add_post

sealed class AddPostEvent {
    data class OnTitleChange(val title: String) : AddPostEvent()
    data class OnContentChange(val content: String) : AddPostEvent()
    object OnAddClick : AddPostEvent()
    object OnCancelClick : AddPostEvent()
}