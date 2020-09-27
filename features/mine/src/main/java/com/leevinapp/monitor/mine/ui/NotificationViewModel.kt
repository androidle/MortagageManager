package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val freshData = MutableLiveData(true)

    val notificationsResult = MutableLiveData<MutableList<NotificationModel>>()

    fun getNotifications() {
        if (freshData.value != true) return
        repository.getNotifications()
            .applyIoWithLoading()
            .subscribe({ response ->
                if (!response.success) {
                    errorMessage.postValue(response.error)
                } else {
                    notificationsResult.value = convertToModel(response.data)
                }
            }, {})
            .addTo(compositeDisposable)
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
