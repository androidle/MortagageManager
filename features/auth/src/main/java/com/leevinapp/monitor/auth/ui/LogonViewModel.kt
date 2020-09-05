package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.repository.AuthRepository
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class LogonViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val phoneNumber = MutableLiveData<String>("")
    val smsCode = MutableLiveData("")
    val password = MutableLiveData("")

    val logonSuccess = MutableLiveData<Boolean>(false)

    fun login() {
        authRepository.login(phoneNumber.value ?: "", password.value ?: "", smsCode.value ?: "")
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                logonSuccess.postValue(true)
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
                logonSuccess.postValue(false)
            })
    }

    fun sendSmsCode() {
        val params =
            SendSmsCodeParams(telephone = phoneNumber.value ?: "", smsType = "REGISTER")
        authRepository.sendSmsCode(params)
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }

    val username = MutableLiveData("hello")
}
