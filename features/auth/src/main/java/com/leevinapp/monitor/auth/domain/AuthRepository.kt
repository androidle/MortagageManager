package com.leevinapp.monitor.auth.domain

import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single

interface AuthRepository {

    fun registerUser(registerUserParams: RegisterUserParams): Single<String>

    fun login(phoneNumber: String, password: String, smsCode: String): Single<LoginResponse>

    fun logout(): Single<ApiResponse<Any>>

    fun sendSmsCode(phoneNumber: String, smsType: String): Single<Boolean>

    fun sendEmailCode(email: String): Single<Boolean>

    fun changePassword(changePasswordParams: ChangePasswordParams): Single<ApiResponse<Any>>

    fun resetPassword(resetPasswordParams: ResetPasswordParams): Single<Boolean>

    fun verifyNewEmail(params: VerifyNewEmailParams): Single<Boolean>
}
