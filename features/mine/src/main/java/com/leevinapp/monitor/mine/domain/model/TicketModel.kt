package com.leevinapp.monitor.mine.domain.model

import com.leevinapp.monitor.mine.domain.model.TicketStatus.AUDITING

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
)
