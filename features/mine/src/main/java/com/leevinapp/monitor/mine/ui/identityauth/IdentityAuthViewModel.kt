package com.leevinapp.monitor.mine.ui.identityauth

import androidx.lifecycle.MediatorLiveData
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

    val userRoleDes = MediatorLiveData<String>().apply {
        addSource(_userRole) {
            value = _userRole.value?.desc
        }
    }

    val identityNum = MutableLiveData("")
    val homeAddress = MutableLiveData("")
    val companyName = MutableLiveData("")
    val jobPosition = MutableLiveData("")

    val supervisorSocialCode = MutableLiveData("")

    val organAddress = MutableLiveData("")
    val supervisorOrgan = MutableLiveData("")
    val organRepresentative = MutableLiveData("")
    val registeredCapital = MutableLiveData("")
    val dateOfFounded = MutableLiveData("")
    val businessPeriod = MutableLiveData("")
    val businessScope = MutableLiveData("")

    val verifyUserResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val verifyOrganResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun verifyOrdinaryUser() {
        val params = VerifyUserParams(userRole = _userRole.value ?: UserRole.BANK_USER).apply {
            residenceId = identityNum.value
            familyAddress = homeAddress.value
            organizationName = companyName.value
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

    fun verifyMortgageUser() {
        val params = VerifyUserParams(userRole = _userRole.value ?: UserRole.BANK_USER_NO_ORG).apply {
            residenceId = identityNum.value
            familyAddress = homeAddress.value
            organizationName = companyName.value
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
            name = supervisorOrgan.value
            uniformSocialCreditCode = supervisorSocialCode.value
            address = organAddress.value
            legalRepresentative = organRepresentative.value
            registeredCapital = this@IdentityAuthViewModel.registeredCapital.value
            registeredDate = dateOfFounded.value
            businessPeriod = this@IdentityAuthViewModel.businessPeriod.value
            businessScope = this@IdentityAuthViewModel.businessScope.value
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
