package com.leevinapp.monitor.core.core.user

import com.leevinapp.monitor.core.core.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(private val storage: Storage) {

    var isLogged = false
    var isAutoLogin = false
    var user = UserModel()
    var token = ""
    fun reset() {
        isLogged = false
        user = UserModel()
    }
}
