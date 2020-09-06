package com.leevinapp.monitor.mine.data.response

data class UpdateUserProfileParams(
    var confirmPassword: String,
    var email: String,
    var fullName: String,
    var homeAddress: String,
    var id: String,
    var isAuthenticated: Boolean,
    var jobPosition: String,
    var nickname: String,
    var organizationId: String,
    var organizationName: String,
    var password: String,
    var residenceId: String,
    var role: String,
    var securityAnswer: String,
    var securityQuestion: String,
    var telephone: String,
    var watchOrganizationId: Int
)
