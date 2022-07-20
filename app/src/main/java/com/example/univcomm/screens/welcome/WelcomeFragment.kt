package com.example.univcomm.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import com.example.univcomm.R
import com.example.univcomm.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var rootView: View

    private lateinit var binding: FragmentWelcomeBinding

    private lateinit var viewModel: WelcomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        viewModel =
            ViewModelProvider(this)[WelcomeViewModel::class.java]


        rootView = binding.root


        val signInButtonWelcome = binding.signInButtonWelcome
        val signUpButtonWelcome = binding.signUpButtonWelcome

        signInButtonWelcome.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment))
        signUpButtonWelcome.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_registerFragment))

        // Inflate the layout for this fragment
        return rootView
    }
}