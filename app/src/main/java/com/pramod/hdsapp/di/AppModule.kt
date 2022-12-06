package com.pramod.hdsapp.di

import com.google.gson.GsonBuilder
import com.pramod.hdsapp.api.ApiService
import com.pramod.hdsapp.api.AuthIntercepter
import com.pramod.hdsapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val clent = OkHttpClient.Builder().apply {
        addInterceptor(AuthIntercepter())
            .build()
    }

    val gson = GsonBuilder().serializeNulls().setDateFormat(DateFormat.LONG).create()

    @Singleton
    @Provides
    fun getIntance(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiService::class.java)


    }
}
