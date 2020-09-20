package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.params.LoginParams
import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.params.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AuthService {

    @POST("common/auth/login/user")
    fun login(@Body params: LoginParams): Single<ApiResponse<LoginResponse>>

    @PUT("app/auth/register/user")
    fun registerUser(
        @Body params: RegisterUserParams
    ): Single<ApiResponse<RegisterUserResponse>>

    @POST("common/auth/sendSmsCode")
    fun sendSmsCode(
        @Body params: SendSmsCodeParams
    ): Single<ApiResponse<Any>>

    @GET("common/auth/sendEmailVerifyCode")
    fun sendEmailVerifyCode(
        @Query("email") email: String
    ): Single<ApiResponse<Any>>

    @POST("app/user/resetPassword")
    fun resetPassword(@Body params: ResetPasswordParams): Single<ApiResponse<Any>>
}
