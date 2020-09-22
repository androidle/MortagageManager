package com.leevinapp.monitor.mine.data.params

data class UpdateUserProfileParams(
    val email: String? = null,
    val fullName: String? = null,
    val homeAddress: String? = null,
    val jobPosition: String? = null,
    val nickname: String? = null,
    val organizationName: String? = null
)
