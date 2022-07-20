package com.example.univcomm.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class UserLoginBody(
    val email: String, val password: String
)

data class UserLoginResponse(
    val access_token: String,
)

data class UserBaseResponse(
    val message: String,
)

private const val BASE_URL = "https://univcomm.herokuapp.com/api/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes methods
 */
interface UserApiService {
    @GET("user/")
    suspend fun getUserBase(): UserBaseResponse

    @POST("user/login")
    suspend fun userLogin(
        @Body userLoginBody: UserLoginBody
    ): UserLoginResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object UserApi {
    val retrofitService: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}