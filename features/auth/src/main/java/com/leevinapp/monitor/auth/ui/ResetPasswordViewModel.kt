package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.E_MAIL
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.SMS
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject
import timber.log.Timber

class ResetPasswordViewModel @Inject constructor(private val authRepository: AuthRepository) : BaseViewModel() {

    val phoneNumber = MutableLiveData("")
    val smsCode = MutableLiveData("")
    val email = MutableLiveData("")
    val emailVerifyCode = MutableLiveData("")

    var resetType = SMS

    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    val smsCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val emailCodeResult: MutableLiveData<Boolean> by lazy {
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

    fun sendEmailCode() {
        authRepository.sendEmailCode(email.value ?: "")
            .subscribe(Consumer {
                Timber.d("=sendEmailCode===>$it")
                emailCodeResult.postValue(it)
            }, Consumer {
                Timber.e("=sendEmailCode===>$it")
                emailCodeResult.postValue(false)
            })
    }

    fun resetPassword() {
        val resetPasswordParams = buildResetPasswordParams()

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

    private fun buildResetPasswordParams(): ResetPasswordParams {
        val resetPasswordParams = when (resetType) {
            SMS -> {
                ResetPasswordParams(
                    resetPasswordType = resetType.name,
                    newPassword = password.value ?: "",
                    confirmNewPassword = confirmPassword.value ?: "",
                    telephone = phoneNumber.value ?: "",
                    smsVerifyCode = smsCode.value ?: ""
                )
            }
            E_MAIL -> {
                ResetPasswordParams(
                    resetPasswordType = resetType.name,
                    newPassword = password.value ?: "",
                    confirmNewPassword = confirmPassword.value ?: "",
                    email = email.value ?: "",
                    smsVerifyCode = smsCode.value ?: ""
                )
            }
        }
        return resetPasswordParams
    }
}
