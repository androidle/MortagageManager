package com.leevinapp.monitor.auth.data.api.params

data class LoginParams(
    val password: String,
    val smsVerifyCode: String,
    val telephone: String
)
