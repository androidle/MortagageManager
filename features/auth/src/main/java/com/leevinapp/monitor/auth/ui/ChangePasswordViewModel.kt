package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.params.ChangePasswordParams
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val authRepository: PostAuthRepository
) : BaseViewModel() {

    val oldPassword = MutableLiveData("")
    val newPassword = MutableLiveData("")
    val newConfirmPassword = MutableLiveData("")

    val changePasswordResult = MutableLiveData(false)

    fun changePassword() {
        val changePasswordParams =
            ChangePasswordParams(
                password = oldPassword.value ?: "",
                newPassword = newPassword.value ?: "",
                confirmPassword = newConfirmPassword.value ?: ""
            )
        authRepository.changePassword(changePasswordParams)
            .applyIoWithLoading()
            .subscribe(Consumer {
                changePasswordResult.postValue(it.success)
                if (it.success.not()) {
                    errorMessage.postValue(it.error)
                }
            }).addTo(compositeDisposable)
    }
}
