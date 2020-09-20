package com.leevinapp.monitor.auth.data.api.params

data class ChangePasswordParams(
    val confirmPassword: String,
    val newPassword: String,
    val password: String
)
