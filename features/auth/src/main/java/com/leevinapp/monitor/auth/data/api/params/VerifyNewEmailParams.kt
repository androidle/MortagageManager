package com.leevinapp.monitor.auth.data.api.params

data class VerifyNewEmailParams(
    var email: String = "",
    var verifyCode: String = ""
)
