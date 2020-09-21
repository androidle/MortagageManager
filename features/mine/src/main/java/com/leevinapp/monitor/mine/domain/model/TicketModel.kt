package com.leevinapp.monitor.mine.domain.model

import com.leevinapp.monitor.mine.domain.model.TicketStatus.APPROVED
import com.leevinapp.monitor.mine.domain.model.TicketStatus.AUDITING
import com.leevinapp.monitor.mine.domain.model.TicketStatus.REJECTED

class TicketModel(
    var applicant: String = "",
    var phonenumber: String = "",
    var titleName: String = "",
    var date: String = "",
    var id: Long = 0,
    var status: TicketStatus = AUDITING,
    var isExpand: Boolean = false,
    var realname: String = "",
    var identityNumber: String = "",
    var identityType: String = "",
    var homeAddress: String = "",
    var companyName: String = "",
    var socialCode: String = "",
    var jobPosition: String = ""
) {
    fun isApproved(): Boolean = status == APPROVED

    fun isRejected(): Boolean = status == REJECTED

    fun isAUDITING(): Boolean = status == AUDITING
}
