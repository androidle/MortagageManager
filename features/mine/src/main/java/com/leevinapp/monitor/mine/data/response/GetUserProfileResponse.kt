package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserRole

data class GetUserProfileResponse(
    var confirmPassword: String,
    var email: String,
    var fullName: String?,
    var nickname: String?,
    var homeAddress: String?,
    var id: Long,
    var isAuthenticated: Boolean,
    var jobPosition: String?,
    var organizationId: Long?,
    var organizationName: String,
    var password: String,
    var residenceId: Long?,
    var role: UserRole,
    var telephone: String,
    var watchOrganizationId: Long?
)
