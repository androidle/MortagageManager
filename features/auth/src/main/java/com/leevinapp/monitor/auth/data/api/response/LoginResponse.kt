package com.leevinapp.monitor.auth.data.api.response

data class LoginResponse(
    var confirmPassword: String,
    var email: String?,
    var fullName: String?,
    var homeAddress: String?,
    var id: Long,
    var isAuthenticated: Boolean,
    var jobPosition: String?,
    var nickname: String?,
    var organizationId: Long?,
    var organizationName: String?,
    var password: String?,
    var residenceId: Long?,
    var role: String?,
    var securityAnswer: String?,
    var securityQuestion: String?,
    var telephone: String,
    var token: String,
    var watchOrganizationId: Long?
)
