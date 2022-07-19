package com.example.univcomm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_welcome, container, false)


        val signInButtonWelcome = rootView.findViewById<Button>(R.id.signInButtonWelcome)
        val signUpButtonWelcome = rootView.findViewById<Button>(R.id.signUpButtonWelcome)

        signInButtonWelcome.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment))
        signUpButtonWelcome.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_registerFragment))


        // Inflate the layout for this fragment
        return rootView
    }
}