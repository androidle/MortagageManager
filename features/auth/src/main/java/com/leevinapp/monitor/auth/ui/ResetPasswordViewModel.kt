package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.EMAIL
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.SMS
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.utils.vaidation.EmailRule
import com.leevinapp.monitor.core.core.utils.vaidation.PhoneNumberRule
import com.leevinapp.monitor.core.core.utils.vaidation.Validator
import com.leevinapp.monitor.core.core.utils.vaidation.VerifyCodeRule
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

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
        authRepository.sendSmsCode(phoneNumber.value ?: "", SMSType.RESET_PWD)
            .applyIoWithoutLoading()
            .subscribe(Consumer { response ->
                smsCodeResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            })
            .addTo(compositeDisposable)
    }

    fun sendEmailCode() {
        authRepository.sendEmailCode(email.value ?: "")
            .applyIoWithoutLoading()
            .subscribe({ response ->
                emailCodeResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    fun resetPassword() {

        val resetPasswordParams = buildResetPasswordParams()

        authRepository.resetPassword(resetPasswordParams)
            .applyIoWithLoading()
            .subscribe({ response ->
                resetPasswordResultResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    private fun buildResetPasswordParams(): ResetPasswordParams {
        return when (resetType) {
            SMS -> {
                ResetPasswordParams(
                    resetPasswordType = resetType,
                    newPassword = password.value ?: "",
                    confirmNewPassword = confirmPassword.value ?: "",
                    telephone = phoneNumber.value,
                    verifyCode = smsCode.value ?: ""
                )
            }
            EMAIL -> {
                ResetPasswordParams(
                    resetPasswordType = resetType,
                    newPassword = password.value ?: "",
                    confirmNewPassword = confirmPassword.value ?: "",
                    email = email.value,
                    verifyCode = emailVerifyCode.value ?: ""
                )
            }
        }
    }

    fun validate(): Boolean {
        val validatorSms = Validator().addRule(PhoneNumberRule(phoneNumber.value ?: ""), VerifyCodeRule(smsCode.value ?: ""))
            .addErrorCallback {
                errorMessage.value = it
            }
        val validatorEmail = Validator().addRule(EmailRule(email.value ?: ""), VerifyCodeRule(emailVerifyCode.value ?: ""))
            .addErrorCallback {
                errorMessage.value = it
            }
        return when (resetType) {
            SMS -> {
                validatorSms.check()
            }
            EMAIL -> {
                validatorEmail.check()
            }
        }
    }
}
