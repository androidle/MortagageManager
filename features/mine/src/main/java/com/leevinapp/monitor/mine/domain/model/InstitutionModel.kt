package com.leevinapp.monitor.mine.domain.model

import com.leevinapp.monitor.core.core.user.UserRole
import java.util.Date

class InstitutionModel(
    var address: String = "",
    var adminName: String = "",
    var adminUserId: Long = 0,
    var businessPeriod: String = "",
    var businessScope: String = "",
    var companyRegisterType: String = "",
    var id: Long = 0,
    var legalRepresentative: String = "",
    var institutionName: String = "",
    var parentId: Long = 0,
    var registeredCapital: String="",
    var registeredDate: Date?=null,
    var role: UserRole? = null,
    var status: TicketStatus?= null,
    var uniformSocialCreditCode: String = "",

    var isExpand: Boolean = false
) : SearchResult {

    override fun getName(): String {
        return institutionName
    }

    override fun getValue(): String {
        return id.toString()
    }
}
