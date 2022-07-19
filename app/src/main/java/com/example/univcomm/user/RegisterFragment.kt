package com.example.univcomm.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.univcomm.R


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_register, container, false)


        val orSignInButton = rootView.findViewById<Button>(R.id.orSignInButton)



        orSignInButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_registerFragment_to_loginFragment))

        // Inflate the layout for this fragment
        return rootView
    }
}