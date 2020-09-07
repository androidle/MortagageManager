package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeResponse
import com.leevinapp.monitor.auth.data.api.response.TestResponse
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthService {
    @GET("users/list")
    fun test(): Single<TestResponse>

    @POST("common/auth/login/user")
    fun login(@Body params: LoginParams): Single<ApiResponse<LoginResponse>>

    @POST("common/auth/logout/user/{userId}") // TODO: 2020/9/5  params
    fun logout(@Body params: LoginParams): Single<LoginResponse>

    @POST("common/auth/sendSmsCode")
    fun sendSmsCode(
        @Body params: SendSmsCodeParams
    ): Single<SendSmsCodeResponse>

    @PUT("app/auth/register/user")
    fun registerUser(
        @Body params: RegisterUserParams
    ): Single<ApiResponse<RegisterUserResponse>>

    @FormUrlEncoded
    @POST("common/auth/logout/user/{userId}")
    fun logout(
        @Path("userId") userId: String,
        @Header("token") token: String
    ): Single<ApiResponse<Any>>

    @POST("app/user/changePassword")
    fun changePassword(@Body params: ChangePasswordParams): Single<ApiResponse<Any>>
}
