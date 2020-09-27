package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserModel
import com.leevinapp.monitor.core.core.user.UserRole

data class GetUserProfileResponse(
    var email: String?,
    var fullName: String?,
    var nickname: String?,
    var homeAddress: String?,
    var id: Long,
    var isAuthenticated: Boolean,
    var jobPosition: String?,
    var organizationId: Long?,
    var organizationName: String?,
    var residenceId: String?,
    var role: UserRole,
    var telephone: String,
    var uniformSocialCreditCode: String? = null,
    var watchOrganizationId: Long?
) {

    fun toModel(): UserModel {
        return UserModel().apply {
            id = this@GetUserProfileResponse.id
            phoneNumber = this@GetUserProfileResponse.telephone
            email = this@GetUserProfileResponse.email ?: ""
            fullName = this@GetUserProfileResponse.fullName ?: ""
            nickname = this@GetUserProfileResponse.nickname ?: ""
            homeAddress = this@GetUserProfileResponse.homeAddress ?: ""
            isAuthenticated = this@GetUserProfileResponse.isAuthenticated
            jobPosition = this@GetUserProfileResponse.jobPosition ?: ""
            organName = this@GetUserProfileResponse.organizationName ?: ""
            residenceId = this@GetUserProfileResponse.residenceId ?: ""
            role = this@GetUserProfileResponse.role
            socialCode = this@GetUserProfileResponse.uniformSocialCreditCode
            watchOrganizationId = this@GetUserProfileResponse.watchOrganizationId ?: 0
        }
    }
}
