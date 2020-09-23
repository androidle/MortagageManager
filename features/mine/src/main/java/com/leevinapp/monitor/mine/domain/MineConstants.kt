package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.core.core.user.UserRole.BANK
import com.leevinapp.monitor.core.core.user.UserRole.BANK_USER
import com.leevinapp.monitor.core.core.user.UserRole.BORROWER
import com.leevinapp.monitor.core.core.user.UserRole.BORROWER_USER
import com.leevinapp.monitor.core.core.user.UserRole.SUPERVISOR
import com.leevinapp.monitor.core.core.user.UserRole.SUPERVISOR_USER

object MineConstants {
    val auth_ways = arrayOf("普通用户认证", "质权人独立用户认证", "注册机构认证")
    val user_identity_types = arrayOf(BANK_USER.desc, BORROWER_USER.desc, SUPERVISOR_USER.desc)
    val organ_identity_types = arrayOf(BANK.desc, BORROWER.desc, SUPERVISOR.desc)
}
