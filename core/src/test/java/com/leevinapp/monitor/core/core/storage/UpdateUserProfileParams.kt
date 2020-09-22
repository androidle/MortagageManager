package com.leevinapp.monitor.core.core.storage

import com.leevinapp.monitor.core.core.user.UserRole
import com.leevinapp.monitor.core.core.user.UserRole.ADMIN_BANK

data class UpdateUserProfileParams(
    var confirmPassword: String = "",
    var email: String = "",
    var fullName: String = "",
    var homeAddress: String = "",
    var id: Long,
    var isAuthenticated: Boolean = false,
    var jobPosition: String = "",
    var nickname: String = "",
    var organizationId: Long = 0,
    var organizationName: String = "",
    var password: String = "",
    var residenceId: Long = 0,
    var identityNumber: String = "",
    var role: UserRole = ADMIN_BANK,
    var securityAnswer: String = "",
    var securityQuestion: String = "",
    var telephone: String = "",
    var watchOrganizationId: Long = 0,
    var socialCode: String = ""
)
