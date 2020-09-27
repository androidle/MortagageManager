package com.leevinapp.monitor.mine.domain.model

import com.leevinapp.monitor.core.core.user.UserRole
import java.util.Date

class InstitutionModel(
    var address: String = "",
    var adminName: String? = null,
    var adminUserId: Long = 0,
    var businessPeriod: String = "",
    var businessScope: String = "",
    var companyRegisterType: String = "",
    var id: Long = 0,
    var legalRepresentative: String = "",
    var name: String = "",
    var parentId: Long = 0,
    var registeredCapital: String = "",
    var registeredDate: Date? = null,
    var role: UserRole? = null,
    var status: TicketStatus? = null,
    var uniformSocialCreditCode: String? = null,
    var isExpand: Boolean = false
) : SearchResult
