package com.leevinapp.monitor.mine.data.response

data class GetUserProfileResponse(
    val confirmPassword: String,
    val email: String,
    val fullName: String,
    val homeAddress: String,
    val id: Int,
    val isAuthenticated: Boolean,
    val jobPosition: String,
    val nickname: String,
    val organizationId: Int,
    val organizationName: String,
    val password: String,
    val residenceId: String,
    val role: String,
    val securityAnswer: String,
    val securityQuestion: String,
    val telephone: String,
    val watchOrganizationId: Int
)
