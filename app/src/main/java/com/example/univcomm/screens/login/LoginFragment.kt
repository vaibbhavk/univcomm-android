package com.example.univcomm.screens.login


import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.univcomm.R
import com.example.univcomm.databinding.ActivityMainBinding
import com.example.univcomm.databinding.FragmentLoginBinding
import com.example.univcomm.network.UserApi.retrofitService
import com.example.univcomm.network.UserLoginBody
import kotlinx.coroutines.*


class LoginFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


        rootView = binding.root


        val signInButton = binding.signInButton
        val orSignUpButton = binding.orSignUpButton


        orSignUpButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment))

        signInButton.setOnClickListener {
            println("inside onlick continue login")
//            makeJsonRequest()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        // Inflate the layout for this fragment
        return rootView
    }


    private fun makeJsonRequest() {
        val viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        coroutineScope.launch {

            val response =
                retrofitService.userLogin(
                    userLoginBody = UserLoginBody(
                        email = "vaibhav.vk2128@gmail.com",
                        password = "123456789"
                    )
                )


            try {
                println(response.access_token)
            } catch (e: Exception) {
                println(e.message)
            } finally {
                viewModelJob.cancel()
            }
        }


    }


}

