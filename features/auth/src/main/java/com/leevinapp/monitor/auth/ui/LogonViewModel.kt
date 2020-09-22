package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

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
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<LoginResponse>> {
                override fun onSuccess(response: ApiResponse<LoginResponse>) {
                    Timber.d("====>$response")
                    if (response.success) {
                        loginResponse.postValue(response.data)
                    } else {
                        loginResponse.postValue(null)
                        errorMessage.postValue(response.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.LOGIN)
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    Timber.d("====>$response")
                    smsCodeResult.postValue(response.success)
                    if (!response.success) {
                        errorMessage.postValue(response.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.e("====>${e.message}")
                }
            })
    }
}
