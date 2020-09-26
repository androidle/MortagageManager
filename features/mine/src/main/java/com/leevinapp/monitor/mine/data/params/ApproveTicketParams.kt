package com.leevinapp.monitor.mine.data.params

data class ApproveTicketParams(
    val comment: String?=null,
    val ticketId: Long,
    val toApprove: Boolean
)
