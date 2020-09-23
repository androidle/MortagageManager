package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.core.core.user.UserRole

data class VerifyOrganizationParams(
    var address: String? = null,
    var adminUserEmail: String? = null,
    var adminUserJobPosition: String? = null,
    var adminUserName: String? = null,
    var adminUserTelephone: String? = null,
    var businessPeriod: String? = null,
    var businessScope: String? = null,
    var companyRegisterType: String? = null,
    var legalRepresentative: String? = null,
    var name: String? = null,
    var registeredCapital: String? = null,
    var registeredDate: String? = null,
    var role: UserRole,
    var uniformSocialCreditCode: String? = null

)
