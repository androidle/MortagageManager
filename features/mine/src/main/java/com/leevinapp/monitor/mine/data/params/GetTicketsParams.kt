package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.mine.domain.model.TicketStatus

data class GetTicketsParams(
    val current: Int,
    val pageSize: Int,
    val status: TicketStatus
)
