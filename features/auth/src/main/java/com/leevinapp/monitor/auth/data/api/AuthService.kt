package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeResponse
import com.leevinapp.monitor.auth.data.api.response.TestResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @GET("users/list")
    fun test(): Single<TestResponse>

    @GET("app/profile/user/1")
    fun auth(): Single<String>

    @POST("common/auth/sendSmsCode")
    fun sendSmsCode(
        @Body params: SendSmsCodeParams
    ): Single<SendSmsCodeResponse>
}
