package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.EMAIL
import com.leevinapp.monitor.auth.domain.model.ResetPasswordType.SMS
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

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

    fun sendEmailCode() {
        authRepository.sendEmailCode(email.value ?: "")
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    Timber.d("====>$response")
                    emailCodeResult.postValue(response.success)
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

    fun resetPassword() {
        val resetPasswordParams = buildResetPasswordParams()

        authRepository.resetPassword(resetPasswordParams)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    resetPasswordResultResult.postValue(response.success)
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
}
