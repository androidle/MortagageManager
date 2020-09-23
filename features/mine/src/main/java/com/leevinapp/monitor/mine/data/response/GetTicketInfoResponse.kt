package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.mine.domain.model.TicketStatus

data class GetTicketInfoResponse(
    val accountVerifyStatus: TicketStatus,
    val dependOnVerifyStatus: TicketStatus,
    val notifyCount: Int,
    val parentOrgVerifyStatus: TicketStatus,
    val permissionTransferVerifyStatus: TicketStatus,
    val ticketCount: Int
)
