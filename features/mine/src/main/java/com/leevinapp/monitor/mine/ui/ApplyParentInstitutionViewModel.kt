package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
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

    fun getParentInstitution() {
        repository.searchInstitution(query.value ?: "")
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<GetSubInstitutionResponse>> {
                override fun onSuccess(t: ApiResponse<GetSubInstitutionResponse>) {
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

    private fun toInstitutionModel(response: GetSubInstitutionResponse): List<InstitutionModel> {
        val result = mutableListOf<InstitutionModel>()
        result.add(
            InstitutionModel(
                username = response.name,
                institutionName = response.name,
                id = response.id
                )
        )

        return result
    }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
        _query.value = input
    }
}
