package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.RequestTicketResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.domain.model.TicketType.PARENT_ORG_VERIFY
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.Locale
import javax.inject.Inject

class ApplyParentInstitutionViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    private val _query = MutableLiveData<String>()

    val query: LiveData<String> = _query

    private val _result = MutableLiveData<List<InstitutionModel>>()
    val result: LiveData<List<InstitutionModel>> = _result

    val currentRequestInstitution = MutableLiveData(InstitutionModel())

    val requestDesc = MutableLiveData("")

    val requestTicketResult = MutableLiveData(false)

    fun getParentInstitution() {
        repository.searchInstitution(query.value ?: "")
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetSubInstitutionResponse>>> {
                override fun onSuccess(t: ApiResponse<List<GetSubInstitutionResponse>>) {
                    if (t.success) {
                        _result.postValue(toInstitutionModel(t.data))
                    } else {
                        errorMessage.postValue(t.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
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

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
        _query.value = input
    }

    fun requestTicket() {
        repository.requestTicket(
            RequestTicketParams(
                desc = requestDesc.value ?: "",
                targetId = currentRequestInstitution.value?.parentId ?: 0,
                type = PARENT_ORG_VERIFY
            )
        )
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<RequestTicketResponse>> {
                override fun onSuccess(t: ApiResponse<RequestTicketResponse>) {
                    requestTicketResult.postValue(t.success)
                    if (t.success.not()) {
                        errorMessage.postValue(t.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    fun selectedInstitution(it: InstitutionModel) {
        currentRequestInstitution.value = it
    }
}
