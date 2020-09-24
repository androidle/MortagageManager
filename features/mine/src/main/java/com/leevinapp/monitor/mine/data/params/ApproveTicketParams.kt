package com.leevinapp.monitor.mine.data.params

data class ApproveTicketParams(
    val comment: String,
    val ticketId: Int,
    val toApprove: Boolean
)