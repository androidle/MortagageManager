package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import io.reactivex.functions.Consumer
import javax.inject.Inject
import timber.log.Timber

class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val smsCode = MutableLiveData("")

    val registerSuccessToken = MutableLiveData<String>("")

    fun registerUser() {
        val params = RegisterUserParams(
            telephone = phoneNumber.value ?: "",
            smsVerifyCode = smsCode.value ?: "",
            password = password.value ?: "",
            confirmPassword = confirmPassword.value ?: ""
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
                registerSuccessToken.postValue(it)
            }, Consumer {
                Timber.e("====>$it")
                registerSuccessToken.postValue("")
            })
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.REGISTER.name)
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }
}
