package com.leevinapp.monitor.auth.domain

import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single

interface AuthRepository {

    fun registerUser(registerUserParams: RegisterUserParams): Single<ApiResponse<RegisterUserResponse>>

    fun login(phoneNumber: String, password: String, smsCode: String): Single<ApiResponse<LoginResponse>>

    fun sendSmsCode(phoneNumber: String, smsType: String): Single<ApiResponse<Any>>

    fun sendEmailCode(email: String): Single<ApiResponse<Any>>

    fun resetPassword(resetPasswordParams: ResetPasswordParams): Single<ApiResponse<Any>>
}
