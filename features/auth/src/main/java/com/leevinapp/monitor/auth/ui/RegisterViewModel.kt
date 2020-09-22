package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {
    val realName = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val companyName = MutableLiveData("")
    val jobPosition = MutableLiveData("")
    val mail = MutableLiveData("")
    val smsCode = MutableLiveData("")

    val registerSuccess = MutableLiveData<Boolean>(false)

    val smsCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun registerUser() {
        val params =
            RegisterUserParams(
                fullName = realName.value ?: "",
                telephone = phoneNumber.value ?: "",
                smsVerifyCode = smsCode.value ?: "",
                password = password.value ?: "",
                confirmPassword = confirmPassword.value ?: "",
                organizationName = companyName.value ?: "",
                jobPosition = jobPosition.value ?: "",
                email = mail.value ?: ""
            )
        authRepository.registerUser(params)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<RegisterUserResponse>> {
                override fun onSuccess(response: ApiResponse<RegisterUserResponse>) {
                    Timber.d("====>$response")
                    registerSuccess.postValue(response.success)
                    if (!response.success) {
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
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.REGISTER)
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
