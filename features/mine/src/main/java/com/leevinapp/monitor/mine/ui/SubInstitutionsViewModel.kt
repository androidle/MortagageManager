package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class SubInstitutionsViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val subInstitutionsResult = MutableLiveData<List<InstitutionModel>>()

    fun getSubInstitutions() {
        repository.getSubInstitution()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<GetSubInstitutionResponse>> {
                override fun onSuccess(response: ApiResponse<GetSubInstitutionResponse>) {
                    Timber.d("==>$response")
                    if (response.success) {
                        val data = response.data
                        subInstitutionsResult.value = convertToModel(data)
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

    private fun convertToModel(data: GetSubInstitutionResponse): List<InstitutionModel>? {
        // TODO("Not yet implemented")
        return mutableListOf()
    }
}
