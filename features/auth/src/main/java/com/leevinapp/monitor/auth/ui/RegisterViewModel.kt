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
    val securityQuestion = MutableLiveData("")
    val securityAnswer = MutableLiveData("")
    val smsCode = MutableLiveData("")

    val registerSuccessToken = MutableLiveData<String>("")

    val smsCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun registerUser() {
        val params =
            RegisterUserParams(
                realName = realName.value ?: "",
                telephone = phoneNumber.value ?: "",
                smsVerifyCode = smsCode.value ?: "",
                password = password.value ?: "",
                confirmPassword = confirmPassword.value ?: "",
                securityQuestion = securityQuestion.value ?: "",
                securityAnswer = securityAnswer.value ?: "",
                organizationName = companyName.value ?: "",
                jobPosition = jobPosition.value ?: "",
                email = mail.value ?: ""
            )
        authRepository.registerUser(params)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<RegisterUserResponse>> {
                override fun onSuccess(response: ApiResponse<RegisterUserResponse>) {
                    Timber.d("====>$response")
                    if (response.success) {
                        registerSuccessToken.postValue(response.data.token)
                    } else {
                        errorMessage.postValue(response.error)
                        registerSuccessToken.postValue("")
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    registerSuccessToken.postValue("")
                }
            })
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.REGISTER.name)
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
