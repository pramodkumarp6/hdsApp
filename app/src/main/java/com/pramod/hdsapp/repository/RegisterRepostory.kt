package com.pramod.hdsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pramod.hdsapp.api.ApiService
import com.pramod.hdsapp.model.register.RegisterResponse
import com.pramod.hdsapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class RegisterRepostory @Inject constructor(private val apiService: ApiService) {

    private val registerData = MutableLiveData<Resource<RegisterResponse>>()
    val userReg: LiveData<Resource<RegisterResponse>>
        get() = registerData




    suspend fun uRegister(email: String, password: String, name: String, school: String) {
        val response= apiService.UserRegister(email,password,name,school)
        RegisterHandler(response)
    }

    private fun RegisterHandler(response: Response<RegisterResponse>) {
        val registerRes = response.body()
        if (!response.body()?.error!!) {
            registerData.postValue(Resource.Success(registerRes!!))
            Log.e("responseBody: ", registerRes.toString())

        } else {

            registerData.postValue(Resource.Error(registerRes!!.message))

            Log.e("else: ", registerRes.message.toString())


        }
    }
}