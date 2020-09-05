package com.leevinapp.monitor.core.core.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    var isLogged = false
    var user = UserModel()
    var token = ""
    fun reset() {
        isLogged = false
        user = UserModel()
    }
}
