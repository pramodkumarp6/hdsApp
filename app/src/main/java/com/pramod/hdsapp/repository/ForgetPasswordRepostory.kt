package com.pramod.hdsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pramod.hdsapp.api.ApiService
import com.pramod.hdsapp.model.forgetuser.ForgetOtpResponse
import com.pramod.hdsapp.model.forgetuser.ForgetResponse
import com.pramod.hdsapp.model.forgetuser.ForgetUpdateResponse
import com.pramod.hdsapp.model.login.LoginResponse
import com.pramod.hdsapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class ForgetPasswordRepostory @Inject constructor(private val apiService: ApiService) {

    private val forgetUser = MutableLiveData<Resource<ForgetResponse>>()
    val userFogetResponse: LiveData<Resource<ForgetResponse>>
        get() = forgetUser

    private val forgetuserOtp = MutableLiveData<Resource<ForgetOtpResponse>>()
    val userOtpResponseLiveData: LiveData<Resource<ForgetOtpResponse>>
        get() = forgetuserOtp


    private val forgetUpdatePassword = MutableLiveData<Resource<ForgetUpdateResponse>>()
    val userUpdateResponseLiveData: LiveData<Resource<ForgetUpdateResponse>>
        get() = forgetUpdatePassword


    suspend fun userforget(email: String) {
        val response = apiService.userPasswordforget(email)

        forgetPasswordHandler(response)

    }

    private fun forgetPasswordHandler(response: Response<ForgetResponse>) {
        val forgetRes = response.body()
        if (!response.body()?.error!!) {
            forgetUser.postValue(Resource.Success(forgetRes!!))
            Log.e("responseBody: ", forgetRes.toString())

        } else {

            forgetUser.postValue(Resource.Error(forgetRes!!.message))

            Log.e("else: ", forgetRes.message.toString())


        }
    }

    suspend fun userForgetOtp(otp: String) {
        val response = apiService.userPasswordOtp(otp)

        forgetOtphandler(response)
    }

    private fun forgetOtphandler(response: Response<ForgetOtpResponse>) {
        val forgetRes = response.body()
        if (!response.body()?.error!!) {
            forgetuserOtp.postValue(Resource.Success(forgetRes!!))
            Log.e("responseBody: ", forgetRes.toString())

        } else {

            forgetuserOtp.postValue(Resource.Error(forgetRes!!.message))

            Log.e("else: ", forgetRes.message.toString())


        }
    }

    suspend fun userforgetchange(password: String, passwordConfirm: String) {
        val response = apiService.userPasswordUpdate(password, passwordConfirm)
        passwordUpdateHandler(response)
    }

    private fun passwordUpdateHandler(response: Response<ForgetUpdateResponse>) {
        val forgetRes = response.body()
        if (!response.body()?.error!!) {
            forgetUpdatePassword.postValue(Resource.Success(forgetRes!!))
            Log.e("responseBody: ", forgetRes.toString())

        } else {

            forgetUpdatePassword.postValue(Resource.Error(forgetRes!!.message))

            Log.e("else: ", forgetRes.message.toString())


        }
    }


}