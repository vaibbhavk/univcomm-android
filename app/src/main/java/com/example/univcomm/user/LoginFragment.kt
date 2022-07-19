package com.example.univcomm.user


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.univcomm.R
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*


interface UserService {
    @POST("login")
    @FormUrlEncoded
    fun registrationPostAsync(
        @Field("email") email: String,
        @Field("password") password: String
    ): Deferred<LoginResponse>
}


data class LoginResponse(
    val access_token: String,
)

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)


        val signInButton = rootView.findViewById<Button>(R.id.signInButton)
        val orSignUpButton = rootView.findViewById<Button>(R.id.orSignUpButton)

        orSignUpButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment))


        signInButton.setOnClickListener {
            makeJsonRequest()
        }

        // Inflate the layout for this fragment
        return rootView
    }


    private fun makeJsonRequest() {

        val viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        val BASE_URL = "https://my-portfolio-2128.herokuapp.com/api/admin/"

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()



        coroutineScope.launch {
            val getPropertiesDeferred = retrofit.create(
                UserService::class.java
            ).registrationPostAsync("vaibhav.vk2128@gmail.com", "123456789")


            try {
                val listResult = getPropertiesDeferred.await()

                println("TOKEN: " + listResult.access_token)

                Toast.makeText(context, "TOKEN: ${listResult.access_token}", Toast.LENGTH_SHORT)
                    .show()


            } catch (e: Exception) {
                println(e)
            } finally {
                viewModelJob.cancel()

            }


        }


    }


}

