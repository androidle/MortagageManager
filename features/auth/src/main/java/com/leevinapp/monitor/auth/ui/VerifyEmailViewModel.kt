package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

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
            .applyIoWithoutLoading()
            .subscribe(Consumer { response ->
                emailCodeResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            })
            .addTo(compositeDisposable)
    }

    fun verifyEmail() {
        postAuthRepository.verifyNewEmail(
            VerifyNewEmailParams(
                email = email.value ?: "",
                verifyCode = emailVerifyCode.value ?: ""
            )
        )
            .applyIoWithLoading()
            .subscribe(Consumer { t ->
                verifyEmailResult.postValue(t.success)
                if (!t.success) {
                    errorMessage.postValue(t.error)
                }
            })
            .addTo(compositeDisposable)
    }
}
