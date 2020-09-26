package com.leevinapp.monitor.auth.data.api.response

import com.google.gson.annotations.SerializedName
import com.leevinapp.monitor.core.core.user.UserRole

data class LoginResponse(
    var confirmPassword: String?,
    var email: String?,
    @SerializedName("userFullName")
    var fullName: String?,
    var homeAddress: String?,
    var id: Long,
    var isAuthenticated: Boolean,
    var jobPosition: String?,
    var nickname: String?,
    var organizationId: Long?,
    var organizationName: String?,
    var password: String?,
    var residenceId: String? = null,
    var role: UserRole? = null,
    var telephone: String,
    var token: String,
    var uniformSocialCreditCode: String? = null,
    var watchOrganizationId: Long?
)
