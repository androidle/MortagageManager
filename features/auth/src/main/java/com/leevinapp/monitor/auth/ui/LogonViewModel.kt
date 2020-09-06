package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.AuthModel
import com.leevinapp.monitor.auth.domain.model.SMSType
import io.reactivex.functions.Consumer
import javax.inject.Inject
import timber.log.Timber

class LogonViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val phoneNumber = MutableLiveData<String>("")
    val smsCode = MutableLiveData("")
    val password = MutableLiveData("")

    val authModel = MutableLiveData<AuthModel>(null)

    fun login() {
        authRepository.login(phoneNumber.value ?: "", password.value ?: "", smsCode.value ?: "")
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                authModel.postValue(it)
                Timber.d("====>${it.token}")
            }, Consumer {
                Timber.e("====>$it")
            })
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.LOGIN.name)
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }
}
