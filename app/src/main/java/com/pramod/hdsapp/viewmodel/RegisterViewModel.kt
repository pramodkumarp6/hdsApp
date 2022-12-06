package com.pramod.hdsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pramod.hdsapp.model.login.LoginResponse
import com.pramod.hdsapp.model.register.RegisterResponse
import com.pramod.hdsapp.repository.RegisterRepostory
import com.pramod.hdsapp.repository.UserRepository
import com.pramod.hdsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepostory: RegisterRepostory) :
    ViewModel() {

    val registerdata: LiveData<Resource<RegisterResponse>>
        get() = registerRepostory.userReg


    fun userRegister(email: String, password: String, name: String, school: String) {
        viewModelScope.launch {
            registerRepostory.uRegister(email, password, name, school)
        }

    }
}