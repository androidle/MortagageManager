package com.leevinapp.monitor.core.core.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {

    var isLogged = true
    val username = ""

    fun reset() {
        isLogged = false
    }
}
