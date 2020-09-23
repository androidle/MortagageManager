package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.core.core.user.UserRole
import java.util.Date

data class VerifyOrganizationParams(
    var address: String? = null,
    var adminUserConfirmPassword: String? = null,
    var adminUserEmail: String? = null,
    var adminUserId: String? = null,
    var adminUserJobPosition: String? = null,
    var adminUserName: String? = null,
    var adminUserPassword: String? = null,
    var adminUserTelephone: String? = null,
    var businessPeriod: String? = null,
    var businessScope: String? = null,
    var companyRegisterType: String? = null,
    var legalRepresentative: String? = null,
    var name: String? = null,
    var registeredCapital: String? = null,
    var registeredDate: Date? = null,
    var role: UserRole,
    var uniformSocialCreditCode: String? = null

)
