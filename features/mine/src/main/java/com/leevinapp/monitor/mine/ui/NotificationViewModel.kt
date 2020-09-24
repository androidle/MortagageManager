package com.leevinapp.monitor.mine.ui

import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class NotificationViewModel @Inject constructor(private val repository: MineRepository):BaseViewModel() {


    fun getNotifications() {
        repository.getNotifications()
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<GetNotificationsResponse>> {
                override fun onSuccess(response: ApiResponse<GetNotificationsResponse>) {
                    Timber.d("==>$response")
                    if (!response.success) {
                        errorMessage.postValue(response.error)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}