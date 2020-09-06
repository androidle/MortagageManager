package com.leevinapp.monitor.mine.data.response

data class VerifyOrganizationParams(
    var address: String,
    var adminUserId: String,
    var businessPeriod: String,
    var businessScope: String,
    var companyRegisterType: String,
    var legalRepresentative: String,
    var name: String,
    var registeredCapital: String,
    var registeredDate: String,
    var role: String,
    var uniformSocialCreditCode: String
)
