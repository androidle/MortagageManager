package com.leevinapp.monitor.auth.data.api.response

data class RegisterUserParams(
    val confirmPassword: String = "",
    val email: String = "",
    val jobPosition: String = "",
    val organizationId: Int = 0,
    val organizationName: String = "",
    val password: String = "",
    val securityAnswer: String = "",
    val securityQuestion: String = "",
    val smsVerifyCode: String = "",
    val telephone: String = ""
)
