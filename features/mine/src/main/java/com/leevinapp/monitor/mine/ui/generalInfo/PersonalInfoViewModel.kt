package com.leevinapp.monitor.mine.ui.generalInfo

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class PersonalInfoViewModel @Inject constructor(
    private val mineRepository: MineRepository,
    private val userManager: UserManager
) : BaseViewModel() {

    val updateProfileResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val nickname = MutableLiveData(userManager.user.nickname)
    val realName = MutableLiveData(userManager.user.fullname)
    val identityNumber = MutableLiveData(userManager.user.identityNumber)
    val companyName = MutableLiveData(userManager.user.organName)
    val socialCode = MutableLiveData(userManager.user.socialCode)
    val jobPosition = MutableLiveData(userManager.user.jobPosition)
    val homeAddress = MutableLiveData(userManager.user.homeAddress)
    val email = MutableLiveData(userManager.user.email)

    fun getUserProfile() {
        mineRepository.getUserProfile()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<GetUserProfileResponse> {
                override fun onSuccess(t: GetUserProfileResponse) {
                    Timber.d("==>$t")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.d("==>${e.message}")
                }
            })
    }

    fun updateUserProfile() {
        mineRepository.updateUserProfile(buildUpdateProfileParams())
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    Timber.d("==>$response")
                    updateProfileResult.postValue(response.success)
                    if (!response.success) {
                        errorMessage.postValue(response.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.d("==>${e.message}")
                }
            })
    }

    private fun buildUpdateProfileParams(): UpdateUserProfileParams {
        return UpdateUserProfileParams(
            id = userManager.user.userId,
            nickname = nickname.value ?: "",
            fullName = realName.value ?: "",
            identityNumber = identityNumber.value ?: "",
            organizationName = companyName.value ?: "",
            jobPosition = jobPosition.value ?: "",
            homeAddress = homeAddress.value ?: "",
            email = email.value ?: ""
        )
    }
}
