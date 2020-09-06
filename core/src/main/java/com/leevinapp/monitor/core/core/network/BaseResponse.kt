package com.leevinapp.monitor.core.core.network

open class BaseResponse<out T>(
    val success: Boolean,
    val error: String = "",
    val data: T
)
