package com.leevinapp.monitor.core.core.user

class UserModel(
    var id: Long = 0,
    var role: UserRole? = null,
    var phoneNumber: String = "",
    var nickname: String = "",
    var fullName: String = "",
    var organName: String = "",
    var organId: Long = 0,
    var jobPosition: String = "",
    var email: String = "",
    var isAuthenticated: Boolean = false,
    var residenceId: String = "",
    var homeAddress: String = "",
    var socialCode: String? = null,
    var watchOrganizationId: Long = 0
) {

    fun copy(): UserModel {
        return UserModel().apply {
            id = this@UserModel.id
            role = this@UserModel.role
            phoneNumber = this@UserModel.phoneNumber
            nickname = this@UserModel.nickname
            fullName = this@UserModel.fullName
            organName = this@UserModel.organName
            organId = this@UserModel.organId
            jobPosition = this@UserModel.jobPosition
            email = this@UserModel.email
            isAuthenticated = this@UserModel.isAuthenticated
            residenceId = this@UserModel.residenceId
            homeAddress = this@UserModel.homeAddress
            socialCode = this@UserModel.socialCode
            watchOrganizationId = this@UserModel.watchOrganizationId
        }
    }
}
