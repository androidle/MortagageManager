package com.leevinapp.monitor.mine.domain.model

enum class TicketType(val desc: String) {
    NORMAL_USER_VERIFY("申请注册为本机构用户"),
    BANK_USER_NO_ORG_VERIFY("申请注册为质权人独立用户"),
    ORG_PC_VERIFY("申请注册机构"),
    ORG_MOBILE_VERIFY("申请注册机构"),
    PARENT_ORG_VERIFY("申请成为子机构"),
    PERMISSION_TRANSFER("申请转移管理员权限"),
    DEPEND_ON_ORG_VERIFY("申请挂靠至金融机构"),
    ACCOUNT_VERIFY("")
}
