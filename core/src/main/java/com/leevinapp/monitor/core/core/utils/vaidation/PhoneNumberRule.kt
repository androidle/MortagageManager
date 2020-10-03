package com.leevinapp.monitor.core.core.utils.vaidation

import java.util.regex.Pattern

private const val PHONE_NUMBER_REGEX = "^(1)\\d{10}$"
const val PHONE_NUMBER_INVALID_MESSAGE = "手机号不能为空或格式不正确"

class PhoneNumberRule(
    override var value: String = "",
    override var errorMessage: String = PHONE_NUMBER_INVALID_MESSAGE
) : BaseRule {
    override fun validate(value: String): Boolean {
        return Pattern.compile(PHONE_NUMBER_REGEX).matcher(value).matches()
    }
}
