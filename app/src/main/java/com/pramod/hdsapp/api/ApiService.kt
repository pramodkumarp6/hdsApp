package com.pramod.hdsapp.api

import com.pramod.hdsapp.model.forgetuser.ForgetOtpResponse
import com.pramod.hdsapp.model.forgetuser.ForgetResponse
import com.pramod.hdsapp.model.forgetuser.ForgetUpdateResponse
import com.pramod.hdsapp.model.login.LoginResponse
import com.pramod.hdsapp.model.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("userlogin")
    suspend fun Login(@Field("email") email:String,
                      @Field("password") password:String):Response<LoginResponse>



    @FormUrlEncoded
    @POST("createuser")
    suspend fun UserRegister(@Field("email") email:String,
                      @Field("password") password:String,
                             @Field("name") name:String,
                             @Field("school") school:String):Response<RegisterResponse>




    @FormUrlEncoded
    @POST("passwordForgetUser")
    suspend fun userPasswordforget(@Field("email") email:String):Response<ForgetResponse>

    @FormUrlEncoded
    @POST("passwordVerify")
    suspend fun userPasswordOtp(@Field("otp") otp: String):Response<ForgetOtpResponse>

    @FormUrlEncoded
    @POST("passwordUpdate")
    suspend fun userPasswordUpdate(@Field("password")password: String,
                                   @Field("confirmPassword")passwordConfirm:String)
    :Response<ForgetUpdateResponse>


}