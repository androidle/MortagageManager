package com.leevinapp.monitor.auth.data.api.params

data class RegisterUserParams(
    var confirmPassword: String,
    var email: String,
    var fullName: String,
    var jobPosition: String,
    var organizationId: Long = 0,
    var organizationName: String,
    var password: String,
    var securityAnswer: String = "",
    var securityQuestion: String = "",
    var smsVerifyCode: String,
    var telephone: String
)
