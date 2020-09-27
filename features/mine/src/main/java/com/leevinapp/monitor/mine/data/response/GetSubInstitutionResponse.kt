package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.mine.domain.model.InstitutionModel
import com.leevinapp.monitor.mine.domain.model.TicketStatus
import java.util.Date

data class GetSubInstitutionResponse(
    val address: String,
    val adminName: String?,
    val adminUserId: Long,
    val businessPeriod: String,
    val businessScope: String?,
    val companyRegisterType: String?,
    val id: Long,
    val legalRepresentative: String,
    val name: String?,
    val parentId: Long,
    val registeredCapital: String?,
    val registeredDate: Date,
    val role: UserRole,
    val status: TicketStatus,
    val uniformSocialCreditCode: String?
) {
    fun toModel(): InstitutionModel {
        return InstitutionModel().apply {
            id = this@GetSubInstitutionResponse.id
            adminName = this@GetSubInstitutionResponse.adminName
            adminUserId = this@GetSubInstitutionResponse.adminUserId
            businessPeriod = this@GetSubInstitutionResponse.businessPeriod
            businessScope = this@GetSubInstitutionResponse.businessScope ?: ""
            companyRegisterType = this@GetSubInstitutionResponse.companyRegisterType ?: ""
            legalRepresentative = this@GetSubInstitutionResponse.legalRepresentative
            name = this@GetSubInstitutionResponse.name ?: ""
            parentId = this@GetSubInstitutionResponse.parentId
            registeredCapital = this@GetSubInstitutionResponse.registeredCapital ?: ""
            registeredDate = this@GetSubInstitutionResponse.registeredDate
            role = this@GetSubInstitutionResponse.role
            status = this@GetSubInstitutionResponse.status
            uniformSocialCreditCode = this@GetSubInstitutionResponse.uniformSocialCreditCode ?: ""
        }
    }
}
