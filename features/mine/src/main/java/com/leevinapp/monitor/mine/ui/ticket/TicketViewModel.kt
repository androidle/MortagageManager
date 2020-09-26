package com.leevinapp.monitor.mine.ui.ticket

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.ApproveTicketParams
import com.leevinapp.monitor.mine.data.response.GetTicketDetailsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.TicketModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class TicketViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val ticketsResult = MutableLiveData<MutableList<TicketModel>>()
    
    fun getTickets(status:String) {
        repository.getTickets(status)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<ApiResponse<List<GetTicketDetailsResponse>>> {
                override fun onSuccess(response: ApiResponse<List<GetTicketDetailsResponse>>) {
                    Timber.d("==>$response")
                    if (!response.success) {
                        errorMessage.postValue(response.error)
                    } else {
                        ticketsResult.value = toModelList(response.data)
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
    }

    private fun toModelList(response: List<GetTicketDetailsResponse>): MutableList<TicketModel> {
        val mutableListOf = mutableListOf<TicketModel>()
        response.forEach { data ->
            mutableListOf.add(
                data.toModel()
            )
        }
        return mutableListOf
    }

    fun approveTicket(ticketId: Long) {
        // TODO: 2020/9/25 comment 
        processTicket(ticketId,true,"")
    }

    fun rejectTicket(ticketId: Long) {
        // TODO: 2020/9/25
        processTicket(ticketId,false,"")
    }

    private fun processTicket(ticketId: Long, isApprove: Boolean,comment:String) {
        repository.approveTicket(
            ApproveTicketParams(
                ticketId = ticketId,
                toApprove = isApprove,
                comment = comment
            )
        )
            .applyIoSchedules()
            .subscribe(
                object : SingleObserver<ApiResponse<Any>> {
                    override fun onSuccess(response: ApiResponse<Any>) {
                        Timber.d("==>$response")
                        if (!response.success) {
                            errorMessage.postValue(response.error)
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }
                }
            )
    }


}
