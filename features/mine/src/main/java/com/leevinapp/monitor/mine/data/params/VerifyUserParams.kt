package com.leevinapp.monitor.mine.data.params

import com.leevinapp.monitor.core.core.user.UserRole

data class VerifyUserParams(
    var familyAddress: String,
    var fullName: String,
    var jobPosition: String,
    var organizationName: String,
    var residenceId: String,
    var supervisorOrganizationName: String,
    var supervisorUniformSocialCreditCode: String,
    var uniformSocialCreditCode: String,
    var userRole: UserRole
)
