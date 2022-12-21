package com.example.univcommcompose.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.univcommcompose.common.Constants
import com.example.univcommcompose.data.remote.UnivcommApi
import com.example.univcommcompose.data.repository.PostRepositoryImpl
import com.example.univcommcompose.data.repository.QuestionRepositoryImpl
import com.example.univcommcompose.data.repository.UserRepositoryImpl
import com.example.univcommcompose.domain.repository.PostRepository
import com.example.univcommcompose.domain.repository.QuestionRepository
import com.example.univcommcompose.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideUnivcommApi(): UnivcommApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(UnivcommApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(api: UnivcommApi): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(api: UnivcommApi): QuestionRepository {
        return QuestionRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UnivcommApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    @Named("auth_token")
    fun provideAuthToken(@ApplicationContext context: Context): String {
        val sharedPreferences = context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", "")

        return token!!
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)
    }


}