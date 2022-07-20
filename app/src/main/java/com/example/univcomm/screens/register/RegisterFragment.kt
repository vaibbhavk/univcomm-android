package com.example.univcomm.screens.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.univcomm.R
import com.example.univcomm.databinding.FragmentLoginBinding
import com.example.univcomm.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        rootView = binding.root


        val orSignInButton = binding.orSignInButton


        orSignInButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_registerFragment_to_loginFragment))

        // Inflate the layout for this fragment
        return rootView
    }
}