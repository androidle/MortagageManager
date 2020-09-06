package com.leevinapp.monitor.core.core.network

open class BaseResponse<T>(
    var success: Boolean,
    var error: String = "",
    var data: T
)
