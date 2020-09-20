package com.leevinapp.monitor.mine.data.params

data class VerifyOrganizationParams(
    var familyAddress: String,
    var fullName: String,
    var jobPosition: String,
    var organizationName: String,
    var residenceId: String,
    var supervisorOrganizationName: String,
    var supervisorUniformSocialCreditCode: String,
    var uniformSocialCreditCode: String,
    var userRole: String
)
