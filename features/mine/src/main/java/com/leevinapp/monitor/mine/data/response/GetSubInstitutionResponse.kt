package com.leevinapp.monitor.mine.data.response

import java.util.Date

data class GetSubInstitutionResponse(
    val address: String,
    val adminUserId: Long,
    val businessPeriod: String,
    val businessScope: String,
    val companyRegisterType: String,
    val id: Long,
    val legalRepresentative: String,
    val name: String,
    val parentId: Long,
    val registeredCapital: Date,
    val registeredDate: String,
    val role: String,
    val status: String,
    val uniformSocialCreditCode: String
)
