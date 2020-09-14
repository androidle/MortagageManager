package com.leevinapp.monitor.auth.data.api.response

data class ResetPasswordParams(
    var answer: String = "",
    var confirmNewPassword: String = "",
    var newPassword: String = "",
    var resetPasswordType: String = "",
    var smsVerifyCode: String = "",
    var telephone: String = ""
)
