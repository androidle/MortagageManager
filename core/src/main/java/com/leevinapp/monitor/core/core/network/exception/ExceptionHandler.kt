package com.leevinapp.monitor.core.core.network.exception

interface ExceptionHandler {

    fun handleException(throwable: Throwable): ResponseException
}
