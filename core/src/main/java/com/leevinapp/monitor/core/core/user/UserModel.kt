package com.leevinapp.monitor.core.core.user

import com.leevinapp.monitor.core.core.user.UserRole.ADMIN_BANK

class UserModel(
    var userId: Long = 0,
    var role: UserRole = ADMIN_BANK,
    var phoneNumber: String = "",
    var nickname: String = "",
    var fullname: String = "",
    var organName: String = "",
    var organId: Long = 0,
    var jobPosition: String = "",
    var email: String = "",
    var isAuthenticated: Boolean = false,
    var residenceId: Long = 0,
    var homeAddress: String = "",
    var identityType: String = "",
    var identityNumber: String = "",
    var socialCode: String = "",
    var watchOrganizationId: Long = 0
)
