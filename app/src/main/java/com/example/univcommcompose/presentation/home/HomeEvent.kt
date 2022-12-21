package com.example.univcommcompose.presentation.home

sealed class HomeEvent {
    data class OnTabChange(val index: Int) : HomeEvent()
}