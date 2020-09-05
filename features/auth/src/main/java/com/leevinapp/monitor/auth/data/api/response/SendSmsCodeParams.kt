package com.leevinapp.monitor.auth.data.api.response

data class SendSmsCodeParams(
    val smsType :String = "REGISTER",
    val telephone:String
)