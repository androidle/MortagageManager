package com.leevinapp.monitor.mine.domain.model

import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER
import com.leevinapp.monitor.mine.domain.model.TicketStatus.APPROVED
import com.leevinapp.monitor.mine.domain.model.TicketStatus.AUDITING
import com.leevinapp.monitor.mine.domain.model.TicketStatus.REJECTED
import com.leevinapp.monitor.mine.domain.model.TicketType.NORMAL_USER_VERIFY
import java.util.Date

class TicketModel(
    var id: Long = 0,
    var applicant: String = "",
    var phoneNumber: String = "",
    var titleName: String = "",
    var createDate: Date?= null,
    var updateDate: Date?= null,
    var status: TicketStatus = AUDITING,
    var isExpand: Boolean = false,
    var realname: String = "",
    var identityNumber: String = "",
    var identityType: String = "",
    var homeAddress: String = "",
    var organName: String = "",
    var socialCode: String = "",
    var jobPosition: String = "",
    var role: UserRole = BANK_USER,
    var type:TicketType = NORMAL_USER_VERIFY,
) {
    fun isApproved(): Boolean = status == APPROVED

    fun isRejected(): Boolean = status == REJECTED

    fun isAUDITING(): Boolean = status == AUDITING
}
