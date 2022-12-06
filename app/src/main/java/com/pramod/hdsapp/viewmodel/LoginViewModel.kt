package com.pramod.hdsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pramod.hdsapp.model.login.LoginResponse
import com.pramod.hdsapp.repository.UserRepository
import com.pramod.hdsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<Resource<LoginResponse>>
        get() = userRepository.userLoginResponseLiveData


    fun userLogin(email: String, password: String) {
        viewModelScope.launch {
            userRepository.userlogin(email, password)
        }

    }
}