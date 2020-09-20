package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PostAuthService {

    @POST("common/auth/logout/user/{userId}")
    fun logout(@Path("userId") userId: Long): Single<ApiResponse<Any>>

    @POST("app/user/changePassword")
    fun changePassword(
        @Body params: ChangePasswordParams
    ): Single<ApiResponse<Any>>

    @POST("app/user/verifyNewEmail")
    fun verifyNewEmail(@Body params: VerifyNewEmailParams): Single<ApiResponse<Any>>
}