package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SubInstitutionsViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val subInstitutionsResult = MutableLiveData<MutableList<InstitutionModel>>()

    fun getSubInstitutions() {
        repository.getSubInstitution()
            .applyIoWithLoading()
            .subscribe({ response ->
                if (response.success) {
                    val data = response.data
                    subInstitutionsResult.value = convertToModel(data)
                } else {
                    errorMessage.postValue(response.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    private fun convertToModel(list: List<GetSubInstitutionResponse>): MutableList<InstitutionModel>? {
        val mutableListOf = mutableListOf<InstitutionModel>()
        list.forEach { data ->
            mutableListOf.add(
                data.toModel()
            )
        }
        return mutableListOf
    }
}
