package com.leevinapp.monitor.mine.ui.generalInfo

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.core.core.user.UserModel
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor(
    private val mineRepository: MineRepository,
    private val userManager: UserManager
) : BaseViewModel() {

    val updateProfileResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val userProfile: MutableLiveData<UserModel> by lazy {
        MutableLiveData<UserModel>(userManager.user.copy())
    }

    fun getUserProfile() {
        mineRepository.getUserProfile()
            .applyIoWithLoading()
            .subscribe({ response ->
                if (response.success) {
                    val data = response.data
                    userManager.user = data.toModel()
                    userProfile.postValue(
                        userManager.user
                    )
                } else {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    fun updateUserProfile() {
        mineRepository.updateUserProfile(buildUpdateProfileParams())
            .applyIoWithLoading()
            .subscribe({ response ->
                updateProfileResult.postValue(response.success)
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    private fun buildUpdateProfileParams(): UpdateUserProfileParams {
        return UpdateUserProfileParams().apply {
            nickname = userProfile.value?.nickname
            jobPosition = userProfile.value?.jobPosition
            homeAddress = userProfile.value?.homeAddress
            nickname = userProfile.value?.nickname
            residenceId = userProfile.value?.residenceId
        }
    }
}
