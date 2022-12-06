package com.pramod.hdsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pramod.hdsapp.repository.ForgetPasswordRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetViewModel @Inject constructor(private val forgetPasswordRepostory: ForgetPasswordRepostory) :
    ViewModel() {
    fun forgetUserEmail(email: String) {
        viewModelScope.launch {
            forgetPasswordRepostory.userforget(email)
        }
    }


    fun forgetUserOtp(otp: String) {
        viewModelScope.launch {
            forgetPasswordRepostory.userForgetOtp(otp)
        }
    }

    fun forgetPasswordChange(password: String, passwordConfirm: String) {
        viewModelScope.launch {
            forgetPasswordRepostory.userforgetchange(password, passwordConfirm)

        }
    }


}