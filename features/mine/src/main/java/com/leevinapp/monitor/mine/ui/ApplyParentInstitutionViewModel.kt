package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.domain.model.TicketType.PARENT_ORG_VERIFY
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

open class ApplyParentInstitutionViewModel @Inject constructor(val repository: MineRepository) :
    ApplyTicketViewModel(repository) {

    private val _result = MutableLiveData<List<InstitutionModel>>()
    val result: LiveData<List<InstitutionModel>> = _result

    val currentRequestInstitution = MutableLiveData(InstitutionModel())

    fun searchInstitution() {
        repository.searchInstitution(query.value ?: "")
            .applyIoWithLoading()
            .subscribe(Consumer { t ->
                if (t.success) {
                    _result.postValue(toInstitutionModel(t.data))
                } else {
                    errorMessage.postValue(t.error)
                }
            })
            .addTo(compositeDisposable)
    }

    private fun toInstitutionModel(response: List<GetSubInstitutionResponse>): List<InstitutionModel> {
        val result = mutableListOf<InstitutionModel>()
        response.forEach {
            result.add(
                it.toModel()
            )
        }
        return result
    }

    override fun requestTicketParams(): RequestTicketParams {
        return RequestTicketParams(
            desc = requestDesc.value ?: "",
            targetId = currentRequestInstitution.value?.parentId ?: 0,
            type = PARENT_ORG_VERIFY
        )
    }

    fun targetInstitution(it: InstitutionModel) {
        currentRequestInstitution.value = it
    }
}
