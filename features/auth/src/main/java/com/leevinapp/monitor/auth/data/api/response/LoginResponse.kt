package com.leevinapp.monitor.auth.data.api.response

data class LoginResponse(
    var role: String? = "",
    var telephone: String? = "",
    var token: String? = "",
    var userFullName: String? =""
)
