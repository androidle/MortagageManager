package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.repository.AuthRepository
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class RegisterViewModel  @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val smsCode = MutableLiveData("")

    val registerSuccess = MutableLiveData<Boolean>(false)

    fun registerUser() {
        val  params = RegisterUserParams(
            telephone = phoneNumber.value?:"",
            smsVerifyCode = smsCode.value?:"",
            password = password.value?:"",
            confirmPassword = confirmPassword.value?:""
        )
        authRepository.registerUser(params)
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                Timber.d("====>$it")
                registerSuccess.postValue(true)
            }, Consumer {
                Timber.e("====>$it")
            })

    }

    fun sendSmsCode() {
        val params =
            SendSmsCodeParams(telephone = phoneNumber.value ?: "", smsType = "LOGIN")
        authRepository.sendSmsCode(params)
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }
}