package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.core.core.user.UserRole

data class VerifyUserParams(
    var familyAddress: String? = null,
    var fullName: String? = null,
    var jobPosition: String? = null,
    var organizationName: String? = null,
    var residenceId: String? = null,
    var supervisorOrganizationName: String? = null,
    var supervisorUniformSocialCreditCode: String? = null,
    var uniformSocialCreditCode: String? = null,
    var userRole: UserRole
)
