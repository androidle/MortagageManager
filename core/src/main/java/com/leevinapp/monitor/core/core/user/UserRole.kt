package com.leevinapp.monitor.core.core.user

enum class UserRole {
    ADMIN_PLATFORM,
    ADMIN_BANK,
    ADMIN_BORROWER,
    ADMIN_SUPERVISOR,
    BANK_USER,
    BANK_USER_NO_ORG,
    BORROWER_USER,
    SUPERVISOR_USER,
    SUPERVISOR_PM,

    BANK,
    BORROWER,
    SUPERVISOR;

    fun getDesName(userRole: UserRole): String {
        return when (userRole) {
            BANK_USER -> "金融机构普通用户"
            else -> userRole.name
        }
    }
}
