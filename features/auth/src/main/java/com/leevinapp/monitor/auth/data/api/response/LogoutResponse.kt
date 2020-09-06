package com.leevinapp.monitor.auth.data.api.response

data class LogoutResponse(
    var role: String,
    var telephone: String,
    var token: String,
    var userFullName: String
)
