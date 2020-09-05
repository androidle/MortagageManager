package com.leevinapp.monitor.auth.data.api.response

data class LoginParams(
    val password: String,
    val smsVerifyCode: String,
    val telephone: String
)