package com.leevinapp.monitor.auth.data.api.params

import com.leevinapp.monitor.auth.domain.model.ResetPasswordType

data class ResetPasswordParams(
    var confirmNewPassword: String,
    var email: String? = null,
    var newPassword: String,
    var resetPasswordType: ResetPasswordType,
    var verifyCode: String,
    var telephone: String? = null
)
