package com.leevinapp.monitor.core.core.network.exception

class ResponseException(
    private val type: ExceptionType,
    override val message: String = ""
) : Exception() {

    companion object {
        fun noNetworkException(): ResponseException {
            return ResponseException(ExceptionType.NETWORK, "No Network Connectivity!")
        }
    }
}

enum class ExceptionType {
    NONE, NETWORK, RESULT, JSON_PARSE, OTHER
}
