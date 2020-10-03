package com.leevinapp.monitor.core.core.utils.vaidation

private const val VERIFY_CODE_LENGTH = 6
const val VERIFY_CODE_INVALID_MESSAGE = "验证号不能为空或格式不正确"

class VerifyCodeRule(
    override var value: String = "",
    override var errorMessage: String = VERIFY_CODE_INVALID_MESSAGE
) : BaseRule {
    override fun validate(value: String): Boolean {
        return value.length == VERIFY_CODE_LENGTH
    }
}
