package com.leevinapp.monitor.auth.data.api.params

import com.leevinapp.monitor.auth.domain.model.SMSType

data class SendSmsCodeParams(
    val smsType: SMSType,
    val telephone: String
)
