package com.leevinapp.monitor.auth.data

import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.params.LoginParams
import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.params.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.SMSType
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import io.reactivex.Single

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val userManager: UserManager
) :
    AuthRepository {

    override fun login(
        phoneNumber: String,
        password: String,
        smsCode: String
    ): Single<ApiResponse<LoginResponse>> {
        return authService.login(
            LoginParams(
                telephone = phoneNumber,
                password = password,
                smsVerifyCode = smsCode
            )
        )
    }

    override fun sendSmsCode(phoneNumber: String, smsType: SMSType): Single<ApiResponse<Any>> {
        return authService.sendSmsCode(
            SendSmsCodeParams(
                telephone = phoneNumber,
                smsType = smsType
            )
        )
    }

    override fun sendEmailCode(email: String): Single<ApiResponse<Any>> {
        return authService.sendEmailVerifyCode(email)
    }

    override fun registerUser(registerUserParams: RegisterUserParams): Single<ApiResponse<RegisterUserResponse>> {
        return authService.registerUser(registerUserParams)
    }

    override fun resetPassword(resetPasswordParams: ResetPasswordParams): Single<ApiResponse<Any>> {
        return authService.resetPassword(params = resetPasswordParams)
    }
}
