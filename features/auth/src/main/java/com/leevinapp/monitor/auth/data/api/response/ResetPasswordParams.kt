package com.leevinapp.monitor.auth.data.api.response

data class ResetPasswordParams(
    var confirmNewPassword: String = "",
    var email: String = "",
    var newPassword: String = "",
    var resetPasswordType: String = "",
    var smsVerifyCode: String = "",
    var telephone: String = ""
)
