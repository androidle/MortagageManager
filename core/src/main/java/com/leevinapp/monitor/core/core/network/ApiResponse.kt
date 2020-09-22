package com.leevinapp.monitor.core.core.network

data class ApiResponse<T>(
    var success: Boolean,
    var error: String = "",
    var data: T
)
