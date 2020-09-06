package com.leevinapp.monitor.auth.data.api.response

data class LoginResponse(
    val role: String,
    val telephone: String,
    val token: String,
    val userFullName: String
)
