package com.leevinapp.monitor.mine.ui.identityauth

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.mine.data.params.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.params.VerifyUserParams
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class IdentityAuthViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val realName = MutableLiveData("")
    private val _userRole = MutableLiveData(UserRole.BANK_USER)
    val userRoleDes = MutableLiveData("质权方独立用户")
    val identityNum = MutableLiveData("")
    val homeAddress = MutableLiveData("")
    val companyName = MutableLiveData("")
    val jobPosition = MutableLiveData("")

    val supervisorOrgan = MutableLiveData("")
    val supervisorSocialCode = MutableLiveData("")
    val companyPresenter = MutableLiveData("")
    val registeredCapital = MutableLiveData("")
    val dateOfFounded = MutableLiveData("")
    val operatingPeriod = MutableLiveData("")
    val businessScope = MutableLiveData("")
    val organAdmin = MutableLiveData("")

    val verifyUserResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val verifyOrganResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun verifyUser() {
        val params = VerifyUserParams(userRole = _userRole.value ?: UserRole.BANK_USER).apply {
            familyAddress = homeAddress.value
            residenceId = identityNum.value
            organizationName = companyName.value
            jobPosition = this@IdentityAuthViewModel.jobPosition.value
            supervisorOrganizationName = supervisorOrgan.value
            supervisorUniformSocialCreditCode = supervisorSocialCode.value
        }

        repository.verifyUser(params)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    Timber.d("==>$response")
                    verifyUserResult.postValue(response.success)
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

    fun verifyOrganization() {
        val params = VerifyOrganizationParams(role = _userRole.value ?: UserRole.BANK_USER).apply {
            address = homeAddress.value
            adminUserJobPosition = this@IdentityAuthViewModel.jobPosition.value
            registeredCapital = this@IdentityAuthViewModel.registeredCapital.value
        }

        repository.verifyOrganization(params)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<Any>> {
                override fun onSuccess(response: ApiResponse<Any>) {
                    Timber.d("==>$response")
                    verifyOrganResult.postValue(response.success)
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

    fun setUserRole(userRole: UserRole) {
        this._userRole.value = userRole
    }
}
