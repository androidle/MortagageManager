package com.leevinapp.monitor.mine.data.response

import java.util.Date

data class GetTicketDetailsResponse(
    val approverOrgId: Long,
    val approverRole: String,
    val comment: String,
    val createdAt: String,
    val id: Long,
    val requestReason: String,
    val requestUserId: Long,
    val requestUserName: String,
    val requestUserRole: String,
    val requestUserTelephone: String,
    val status: String,
    val targetResourceId: Long,
    val type: String,
    val updatedAt: Date
)
