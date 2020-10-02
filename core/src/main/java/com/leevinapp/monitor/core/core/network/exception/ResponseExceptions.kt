package com.leevinapp.monitor.core.core.network.exception

import com.leevinapp.monitor.core.core.network.exception.ExceptionType.NETWORK
import com.leevinapp.monitor.core.core.network.exception.ExceptionType.RESULT

open class ResponseException(
    private val type: ExceptionType,
    override val message: String = ""
) : Exception() {

    companion object {
        fun noNetworkException(): ResponseException {
            return NoNetworkConnectionException()
        }

        fun resultException(message: String): ResponseException {
            return ResponseException(RESULT, message)
        }
    }
}

class NoNetworkConnectionException : ResponseException(NETWORK, "No Network Connectivity!")

enum class ExceptionType {
    NONE, NETWORK, RESULT, JSON_PARSE, OTHER
}
