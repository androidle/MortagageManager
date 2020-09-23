package com.leevinapp.monitor.core.core.user

enum class UserRole(val desc: String) {
    ADMIN_PLATFORM(""),
    ADMIN_BANK(""),
    ADMIN_BORROWER(""),
    ADMIN_SUPERVISOR(""),
    BANK_USER("金融机构普通用户"),
    BORROWER_USER("贷款企业普通用户"),
    SUPERVISOR_USER("监管机构普通用户"),
    BANK_USER_NO_ORG("质权方独立用户"),
    SUPERVISOR_PM(""),

    BANK("金融机构"),
    BORROWER("监管机构"),
    SUPERVISOR("贷款企业");
}
