package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionUserModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class InstitutionUserViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val institutionUserResult = MutableLiveData<MutableList<InstitutionUserModel>>(mutableListOf())

    fun getInstitutionUser() {
        repository.getUsersInOrg()
            .applyIoWithLoading()
            .subscribe({ response ->
                if (response.success) {
                    institutionUserResult.value = toModel(response.data)
                } else {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
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
