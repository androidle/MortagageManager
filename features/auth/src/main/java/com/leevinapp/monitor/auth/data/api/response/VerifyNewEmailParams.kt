package com.leevinapp.monitor.auth.data.api.response

data class VerifyNewEmailParams(
    var email: String = "",
    var verifyCode: String = ""
)