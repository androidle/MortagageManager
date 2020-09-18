package com.leevinapp.monitor.mine.domain.model

enum class MenuModel(
    var value: String,
    var content: String = ""
) {
    // about
    SERVICE("服务协议"),
    PRIVACY("隐私政策"),
    VERSION("版权信息"),

    // security
    CHANGE_PASSWORD("修改密码"),
    FORGOT_PASSWORD("找回密码"),
    CHANGE_SECURITY_REF("修改安全问题和答案"),

    //
    PARENT_ORGANIZATION_APPLY("父机构申请"),
    ACCESS_TRANSFER("权限转移"),
    APPLY_REFER_ORGANIZATION("申请挂靠机构"),
    AUTH_ACCOUNT("认证账户")
}
