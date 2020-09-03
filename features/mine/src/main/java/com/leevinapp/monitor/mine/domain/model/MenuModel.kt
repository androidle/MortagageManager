package com.leevinapp.monitor.mine.domain.model

enum class MenuModel(
    val value: String,
    val content: String = ""
) {
    // mine
    PERSONAL_INFORMATION("个人资料"),
    SECURITY_APP("账号安全"),
    PHONE_BIND("手机绑定"),
    AUTHENTICATION("身份认证/企业认证"),
    PASSWORD_MANAGE("修改密码"),
    ABOUT("关于押品管家"),

    // about
    SERVICE("服务协议"),
    PRIVACY("隐私政策"),
    VERSION("版权信息")
}
