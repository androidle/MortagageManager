package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.repository.AuthRepository
import javax.inject.Inject

class RegisterViewModel  @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){

    val phoneNumber = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val smsCode = MutableLiveData<String>("")
}