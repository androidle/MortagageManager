package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.user.UserManager
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userManager: UserManager
) : BaseViewModel() {

    val oldPassword = MutableLiveData("")
    val newPassword = MutableLiveData("")
    val newConfirmPassword = MutableLiveData("")

    val changePasswordResult = MutableLiveData(false)

    fun changePassword() {
        val changePasswordParams = ChangePasswordParams(
            password = oldPassword.value ?: "",
            newPassword = newPassword.value ?: "",
            confirmPassword = newConfirmPassword.value ?: "",
            userId = userManager.user.userId
        )
        authRepository.changePassword(changePasswordParams)
            .applyIoSchedules()
            .subscribe(Consumer {
                changePasswordResult.postValue(true)
                Timber.d("==changePassword==>$it")
            }, Consumer {
                changePasswordResult.postValue(false)
                Timber.d("==changePassword==>$it")
            })
    }
}