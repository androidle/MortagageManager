package com.leevinapp.monitor.core.core.network

class ApiResponse<T>(
    var success: Boolean,
    var error: String = "",
    var data: T
)
