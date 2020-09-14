package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(private val authRepository: AuthRepository) : BaseViewModel() {

    val phoneNumber = MutableLiveData("")
    val smsCode = MutableLiveData("")

    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    val smsCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }
    val resetPasswordResultResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun sendSmsCode() {
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.CHANGE_PWD.name)
            .subscribe(Consumer {
                Timber.d("====>$it")
                smsCodeResult.postValue(it)
            }, Consumer {
                Timber.e("====>$it")
                smsCodeResult.postValue(false)
            })
    }

    fun resetPassword() {
        val resetPasswordParams = ResetPasswordParams(
            resetPasswordType = "SMS",
            newPassword = password.value ?: "",
            confirmNewPassword = confirmPassword.value ?: "",
            telephone = phoneNumber.value ?: "",
            smsVerifyCode = smsCode.value ?: ""
        )
        authRepository.resetPassword(resetPasswordParams)
            .applyIoSchedules()
            .subscribe(Consumer {
                resetPasswordResultResult.postValue(true)
                Timber.d("==resetPassword==>$it")
            }, Consumer {
                resetPasswordResultResult.postValue(false)
                Timber.d("==resetPassword==>$it")
            })
    }
}
