package com.leevinapp.monitor.core.core.user

class UserModel(
    var userId: Long = 0,
    var role: UserRole? = null,
    var phoneNumber: String = "",
    var nickname: String = "",
    var fullname: String = "",
    var organName: String = "",
    var organId: Long = 0,
    var jobPosition: String = "",
    var email: String = "",
    var isAuthenticated: Boolean = false,
    var residenceId: String = "",
    var homeAddress: String = "",
    var uniformSocialCreditCode: String = "",
    var watchOrganizationId: Long = 0
)
