package com.leevinapp.monitor.mine.ui.ticket

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.params.ApproveTicketParams
import com.leevinapp.monitor.mine.data.response.GetTicketDetailsResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.TicketModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TicketViewModel @Inject constructor(private val repository: MineRepository) :
    BaseViewModel() {

    val ticketsResult = MutableLiveData<MutableList<TicketModel>>()

    fun getTickets(status: String) {
        repository.getTickets(status)
            .applyIoWithLoading()
            .subscribe({ response ->
                if (!response.success) {
                    errorMessage.postValue(response.error)
                } else {
                    ticketsResult.value = toModelList(response.data)
                }
            }, {})
            .addTo(compositeDisposable)
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
        processTicket(ticketId, true, "")
    }

    fun rejectTicket(ticketId: Long) {
        // TODO: 2020/9/25
        processTicket(ticketId, false, "")
    }

    private fun processTicket(ticketId: Long, isApprove: Boolean, comment: String) {
        repository.approveTicket(
            ApproveTicketParams(
                ticketId = ticketId,
                toApprove = isApprove,
                comment = comment
            )
        )
            .applyIoWithLoading()
            .subscribe({ response ->
                if (!response.success) {
                    errorMessage.postValue(response.error)
                }
            }, {}
            ).addTo(compositeDisposable)
    }
}
