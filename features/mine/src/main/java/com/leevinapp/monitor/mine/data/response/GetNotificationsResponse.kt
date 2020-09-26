package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.mine.domain.model.NotificationModel
import com.leevinapp.monitor.mine.domain.model.TicketType
import java.util.Date

data class GetNotificationsResponse(
    val approverRole: UserRole?,
    val comment: String?,
    val homeAddress: String?,
    val id: Long,
    val isApproved: Boolean,
    val isRead: Boolean,
    val jobPosition: String?,
    val name: String?,
    val orgId: Long?,
    val orgName: String?,
    val residenceId: String?,
    val role: UserRole,
    val type: TicketType,
    val uniformSocialCreditCode: String?,
    val notifyDate:Date,
    val userId: Long?,
    val telephone:String
) {

    fun toModel(): NotificationModel {
        return NotificationModel(
            applicant = this@GetNotificationsResponse.name ?: "",
            id = this@GetNotificationsResponse.id,
            comment = this@GetNotificationsResponse.comment ?: "",
            isRead = this@GetNotificationsResponse.isRead,
            organName = this@GetNotificationsResponse.orgName ?: "",
            identityNumber = this@GetNotificationsResponse.residenceId ?: "",
            role = this@GetNotificationsResponse.role,
            type = this@GetNotificationsResponse.type,
            socialCode = this@GetNotificationsResponse.uniformSocialCreditCode ?: "",
            jobPosition = this@GetNotificationsResponse.jobPosition ?: "",
            notifyDate = this@GetNotificationsResponse.notifyDate,
            phoneNumber = this@GetNotificationsResponse.telephone,
            homeAddress = this@GetNotificationsResponse.homeAddress?:"",
        )
    }
}
