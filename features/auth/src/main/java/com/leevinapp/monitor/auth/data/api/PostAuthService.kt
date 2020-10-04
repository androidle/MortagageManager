package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.params.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.params.VerifyNewEmailParams
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAuthService {

    @POST("common/auth/logout/user")
    fun logout(): Single<ApiResponse<Any>>

    @POST("app/user/changePassword")
    fun changePassword(
        @Body params: ChangePasswordParams
    ): Single<ApiResponse<Any>>

    @POST("app/user/verifyNewEmail")
    fun verifyNewEmail(@Body params: VerifyNewEmailParams): Single<ApiResponse<Any>>

    @GET("app/user/sendEmailVerifyCode")
    fun sendEmailVerifyCode(@Query("email") email: String): Single<ApiResponse<Any>>
}
