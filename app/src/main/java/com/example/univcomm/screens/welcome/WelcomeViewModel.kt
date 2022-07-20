package com.example.univcomm.screens.welcome

import android.util.Log
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    init {
        Log.i("Welcome", "WelcomeViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Welcome", "WelcomeViewModel destroyed")
    }
}