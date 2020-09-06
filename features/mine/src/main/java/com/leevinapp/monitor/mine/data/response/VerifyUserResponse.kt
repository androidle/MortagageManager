package com.leevinapp.monitor.mine.data.response

data class VerifyUserResponse(
    val role: String,
    val telephone: String,
    val token: String,
    val userFullName: String
)
