package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionUserModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class InstitutionUserViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val institutionUserResult = MutableLiveData<MutableList<InstitutionUserModel>>(mutableListOf())

    fun getInstitutionUser() {
        repository.getUsersInOrg()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetUserProfileResponse>>> {
                override fun onSuccess(response: ApiResponse<List<GetUserProfileResponse>>) {
                    Timber.d("==>$response")
                    if (response.success) {
                        institutionUserResult.value = toModel(response.data)
                    } else {
                        errorMessage.postValue(response.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun toModel(response: List<GetUserProfileResponse>): MutableList<InstitutionUserModel>? {
        val mutableListOf = mutableListOf<InstitutionUserModel>()
        response.forEach { data ->
            mutableListOf.add(
                InstitutionUserModel(
                    userModel = data.toModel()
                )
            )
        }
        return mutableListOf
    }
}