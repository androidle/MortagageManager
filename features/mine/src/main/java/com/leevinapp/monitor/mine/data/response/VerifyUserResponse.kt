package com.leevinapp.monitor.mine.data.response

import com.leevinapp.monitor.core.core.user.UserRole

data class VerifyUserResponse(
    val role: UserRole,
    val telephone: String,
    val token: String,
    val userFullName: String
)
