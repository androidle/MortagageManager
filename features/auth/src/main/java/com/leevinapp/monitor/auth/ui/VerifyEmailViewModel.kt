package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class VerifyEmailViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    val email = MutableLiveData("")
    val emailVerifyCode = MutableLiveData("")

    val emailCodeResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
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

    fun verifyEmail() {
       authRepository.verifyNewEmail(VerifyNewEmailParams(email = email.value?:"",
           verifyCode = emailVerifyCode.value?:""
       ))
           .applyIoSchedules()
           .subscribe(Consumer {
               Timber.d("=verifyEmail===>$it")
               emailCodeResult.postValue(it)
           }, Consumer {
               Timber.e("=verifyEmail===>$it")
               emailCodeResult.postValue(false)
           })

    }
}