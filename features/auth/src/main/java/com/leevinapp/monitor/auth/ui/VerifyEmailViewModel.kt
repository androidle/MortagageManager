package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class VerifyEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val postAuthRepository: PostAuthRepository
) :
    BaseViewModel() {

    val email = MutableLiveData("")
    val emailVerifyCode = MutableLiveData("")

    val emailCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val verifyEmailResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun sendEmailCode() {
        authRepository.sendEmailCode(email.value ?: "")
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(t: ApiResponse<Any>) {
                    Timber.d("=sendEmailCode===>$t")
                    emailCodeResult.postValue(t.success)
                    if (!t.success) {
                        errorMessage.postValue(t.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.d("=sendEmailCode===>${e.message}")
                    errorMessage.postValue(e.message)
                    emailCodeResult.postValue(false)
                }
            })
    }

    fun verifyEmail() {
        postAuthRepository.verifyNewEmail(
            VerifyNewEmailParams(
                email = email.value ?: "",
                verifyCode = emailVerifyCode.value ?: ""
            )
        )
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(t: ApiResponse<Any>) {
                    Timber.d("=verifyEmail===>$t")
                    verifyEmailResult.postValue(t.success)
                    if (!t.success) {
                        errorMessage.postValue(t.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.d("=verifyEmail===>${e.message}")
                    errorMessage.postValue(e.message)
                    verifyEmailResult.postValue(false)
                }
            })
    }
}
