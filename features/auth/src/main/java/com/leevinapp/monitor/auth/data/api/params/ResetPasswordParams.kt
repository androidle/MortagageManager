package com.leevinapp.monitor.auth.data.api.params

data class ResetPasswordParams(
    var confirmNewPassword: String = "",
    var email: String = "",
    var newPassword: String = "",
    var resetPasswordType: String = "",
    var verifyCode: String = "",
    var telephone: String = ""
)
