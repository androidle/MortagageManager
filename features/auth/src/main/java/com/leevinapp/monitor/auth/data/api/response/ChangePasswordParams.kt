package com.leevinapp.monitor.auth.data.api.response

data class ChangePasswordParams(
    val confirmPassword: String,
    val newPassword: String,
    val password: String,
    val userId: Int
)
