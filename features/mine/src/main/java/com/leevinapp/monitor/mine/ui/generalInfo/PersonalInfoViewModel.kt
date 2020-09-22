package com.leevinapp.monitor.mine.ui.generalInfo

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.user.UserModel
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
        MutableLiveData<Boolean>(false)
    }

    val userProfile: MutableLiveData<UserModel> by lazy {
        MutableLiveData<UserModel>(userManager.user)
    }

    fun getUserProfile() {
        mineRepository.getUserProfile()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<GetUserProfileResponse>> {
                override fun onSuccess(response: ApiResponse<GetUserProfileResponse>) {
                    Timber.d("==>$response")
                    if (response.success) {
                        val data = response.data
                        userManager.user = toUserModel(data)
                        userProfile.postValue(
                            userManager.user
                        )
                    } else {
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

    private fun toUserModel(data: GetUserProfileResponse): UserModel {
        return UserModel(
            userId = data.id,
            phoneNumber = data.telephone,
            nickname = data.nickname ?: "",
            fullname = data.fullName ?: "",
            organId = data.organizationId ?: 0,
            jobPosition = data.jobPosition ?: "",
            email = data.email,
            isAuthenticated = data.isAuthenticated,
            residenceId = data.residenceId ?: "",
            homeAddress = data.homeAddress ?: "",
            watchOrganizationId = data.watchOrganizationId ?: 0
        )
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
            jobPosition = userProfile.value?.jobPosition,
            homeAddress = userProfile.value?.homeAddress,
            nickname = userProfile.value?.nickname
        )
    }
}
