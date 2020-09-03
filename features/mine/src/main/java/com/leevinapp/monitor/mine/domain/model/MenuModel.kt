package com.leevinapp.monitor.mine.domain.model

enum class MenuModel(
    var value: String,
    var content: String = ""
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
    VERSION("版权信息"),

    // security
    CHANGE_PASSWORD("修改密码"),
    FORGOT_PASSWORD("找回密码"),
    CHANGE_SECURITY_REF("修改安全问题和答案")
}
