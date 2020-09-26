package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER
import com.leevinapp.monitor.mine.domain.model.TicketModel
import com.leevinapp.monitor.mine.domain.model.TicketStatus
import com.leevinapp.monitor.mine.domain.model.TicketType
import java.util.Date

data class GetTicketDetailsResponse(
    val approverOrgId: Long,
    val approverRole: UserRole,
    val comment: String?,
    val createdAt: Date,
    val id: Long,
    val requestReason: String?,
    val requestUserId: Long,
    val requestUserName: String,
    val requestUserRole: UserRole?,
    val requestUserTelephone: String,
    val status: TicketStatus,
    val targetResourceId: Long,
    val type: TicketType,
    val updatedAt: Date?
) {
    fun toModel(): TicketModel {
        return TicketModel(
            id = this@GetTicketDetailsResponse.id,
            createDate = this@GetTicketDetailsResponse.createdAt,
            role = this@GetTicketDetailsResponse.requestUserRole?:BANK_USER,
            type = this@GetTicketDetailsResponse.type,
            status = this@GetTicketDetailsResponse.status,
        ).apply {
            updateDate = this@GetTicketDetailsResponse.updatedAt
            phoneNumber = this@GetTicketDetailsResponse.requestUserTelephone
            applicant = this@GetTicketDetailsResponse.requestUserName
        }
    }
}
