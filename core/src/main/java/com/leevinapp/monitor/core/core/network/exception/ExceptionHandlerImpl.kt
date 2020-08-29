package com.leevinapp.monitor.core.core.network.exception

import com.google.gson.JsonParseException
import com.leevinapp.monitor.core.core.network.exception.ExceptionType.JSON_PARSE
import com.leevinapp.monitor.core.core.network.exception.ExceptionType.NETWORK
import java.text.ParseException
import javax.net.ssl.SSLHandshakeException
import org.json.JSONException
import retrofit2.HttpException

class ExceptionHandlerImpl : ExceptionHandler {
    override fun handleException(throwable: Throwable): ResponseException {
        var responseException: ResponseException
        when (throwable) {
            is HttpException -> responseException = ResponseException(NETWORK, throwable.message())
            is JsonParseException, is JSONException, is ParseException -> {
                responseException =
                    ResponseException(JSON_PARSE, throwable.message ?: JSON_ERROR_MESSAGE)
            }
            is SSLHandshakeException -> {
                responseException =
                    ResponseException(NETWORK, throwable.message ?: SSL_HAND_SHAKE_ERROR_MESSAGE)
            }
            is NoNetworkConnectionException -> {
                responseException = ResponseException(NETWORK, throwable.message)
            }
            else -> {
                responseException =
                    ResponseException(ExceptionType.OTHER, throwable.message ?: OTHER_ERROR_MESSAGE)
            }
        }
        return responseException
    }

    companion object {
        const val JSON_ERROR_MESSAGE = "Json error"
        const val SSL_HAND_SHAKE_ERROR_MESSAGE = "SSLHandshake error"
        const val OTHER_ERROR_MESSAGE = "SSLHandshake error"
    }
}
