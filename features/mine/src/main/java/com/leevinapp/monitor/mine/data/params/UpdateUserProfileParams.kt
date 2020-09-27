package com.leevinapp.monitor.mine.data.params

data class UpdateUserProfileParams(
    var email: String? = null,
    var fullName: String? = null,
    var homeAddress: String? = null,
    var jobPosition: String? = null,
    var nickname: String? = null,
    var organizationName: String? = null,
    var residenceId: String? = null
)
