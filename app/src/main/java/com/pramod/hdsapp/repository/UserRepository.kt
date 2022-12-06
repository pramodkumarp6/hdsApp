package com.pramod.hdsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pramod.hdsapp.api.ApiService
import com.pramod.hdsapp.model.login.LoginResponse
import com.pramod.hdsapp.model.register.RegisterResponse
import com.pramod.hdsapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    private val _userResponseLiveData = MutableLiveData<Resource<LoginResponse>>()
    val userLoginResponseLiveData: LiveData<Resource<LoginResponse>>
        get() = _userResponseLiveData


    suspend fun userlogin(email: String, password: String) {
        val response = apiService.Login(email, password)
        Loginhandler(response)
    }

    private fun Loginhandler(response: Response<LoginResponse>) {
        val loginResponse = response.body()

        if (!response.body()?.error!!) {
            _userResponseLiveData.postValue(Resource.Success(loginResponse!!))
            Log.e("responseBody: ", loginResponse.toString())

        } else {

            _userResponseLiveData.postValue(Resource.Error(loginResponse!!.message))

            Log.e("else: ", loginResponse.message.toString())


        }
    }


}