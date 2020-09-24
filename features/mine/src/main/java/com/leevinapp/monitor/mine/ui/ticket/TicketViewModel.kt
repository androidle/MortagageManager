package com.leevinapp.monitor.mine.ui.ticket

import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.GetTicketsParams
import com.leevinapp.monitor.mine.data.response.GetTicketDetailsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.TicketStatus
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class TicketViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    fun getTickets() {
        val params = GetTicketsParams(
            current = 0,
            pageSize = 500,
            status = TicketStatus.AUDITING
        )
        repository.getTickets(params)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetTicketDetailsResponse>>> {
                override fun onSuccess(response: ApiResponse<List<GetTicketDetailsResponse>>) {
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