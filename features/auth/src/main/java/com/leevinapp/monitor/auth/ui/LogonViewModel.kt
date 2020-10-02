package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LogonViewModel @Inject constructor(private val authRepository: AuthRepository) : BaseViewModel() {

    val phoneNumber = MutableLiveData<String>("")
    val smsCode = MutableLiveData("")
    val password = MutableLiveData("")

    val loginResponse = MutableLiveData<LoginResponse>(null)

    val smsCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun login() {
        authRepository.login(phoneNumber.value ?: "", password.value ?: "", smsCode.value ?: "")
            .applyIoWithLoading()
            .subscribe({ response ->
                if (response.success) {
                    loginResponse.postValue(response.data)
                } else {
                    loginResponse.postValue(null)
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.LOGIN)
            .applyIoWithoutLoading()
            .subscribe({ response ->
                smsCodeResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {}).addTo(compositeDisposable)
    }
}
