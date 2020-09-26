package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class SubInstitutionsViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val subInstitutionsResult = MutableLiveData<MutableList<InstitutionModel>>()

    fun getSubInstitutions() {
        repository.getSubInstitution()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetSubInstitutionResponse>>> {
                override fun onSuccess(response: ApiResponse<List<GetSubInstitutionResponse>>) {
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
