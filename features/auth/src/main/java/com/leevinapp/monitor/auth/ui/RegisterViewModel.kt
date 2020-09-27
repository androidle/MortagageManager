package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

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
            .applyIoWithLoading()
            .subscribe({ response ->
                registerSuccess.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {}).addTo(compositeDisposable)
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.REGISTER)
            .applyIoWithoutLoading()
            .subscribe(Consumer { response ->
                smsCodeResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            })
            .addTo(compositeDisposable)
    }
}
