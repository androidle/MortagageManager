package com.leevinapp.monitor.core.core.user

class UserModel(
    var phoneNumber: String = "",
    var role: String = "",
    var userId: Long = 0,
    var nickname: String = "",
    var fullname: String = "",
    var organName: String = "",
    var organId: Long = 0,
    var jobPosition: String = "",
    var email: String = "",
    var securityQuestion: String = "",
    var securityAnswer: String = "",
    var isAuthenticated: Boolean = false,
    var residenceId: Long = 0,
    var homeAddress: String = "",
    var identityType: String = "",
    var identityNumber: String = ""
)
