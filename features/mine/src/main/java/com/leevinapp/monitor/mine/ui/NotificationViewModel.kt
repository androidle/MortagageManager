package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val freshData  = MutableLiveData(true)

    val notificationsResult = MutableLiveData<MutableList<NotificationModel>>()

    fun getNotifications() {
        if (freshData.value != true) return
        repository.getNotifications()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetNotificationsResponse>>> {
                override fun onSuccess(response: ApiResponse<List<GetNotificationsResponse>>) {
                    Timber.d("==>$response")
                    if (!response.success) {
                        errorMessage.postValue(response.error)
                    } else {
                        notificationsResult.value = convertToModel(response.data)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun convertToModel(response: List<GetNotificationsResponse>): MutableList<NotificationModel>? {
        val mutableListOf = mutableListOf<NotificationModel>()
        response.forEach { data ->
            mutableListOf.add(
                data.toModel()
            )
        }
        return mutableListOf
    }

    fun stopFresh() {
        freshData.value = false
    }
}
