package com.leevinapp.monitor.core.core.utils.vaidation

import androidx.core.util.PatternsCompat

const val EMAIL_INVALID_MESSAGE = "邮箱地址不能为空或格式不正确"

class EmailRule(
    override var value: String = "",
    override var errorMessage: String = EMAIL_INVALID_MESSAGE
) : BaseRule {
    override fun validate(value: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(value).matches()
    }
}
