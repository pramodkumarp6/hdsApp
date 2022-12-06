package com.pramod.hdsapp.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthIntercepter : Interceptor {
    init{
        System.loadLibrary("keys")
    }
    external fun getApi():String

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Content-type","applicatio/json")
            .addHeader("x-Platform","Android")
            .addHeader("Bearer",getApi())



            .build()

        return chain.proceed(request)



    }
}