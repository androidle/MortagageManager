package com.leevinapp.monitor.auth.data.api.mock

import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeResponse
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import io.reactivex.Single

class AuthMockServiceImpl(private val mockApiUtil: MockApiUtil) :
    AuthService {

    companion object {
        const val TAG_MOCK_API = "Mock response=>:"
    }

    override fun login(params: LoginParams): Single<ApiResponse<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override fun logout(userId: Long): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun sendSmsCode(params: SendSmsCodeParams): Single<SendSmsCodeResponse> {
        TODO("Not yet implemented")
    }

    override fun sendEmailVerifyCode(email: String): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun registerUser(params: RegisterUserParams): Single<ApiResponse<RegisterUserResponse>> {
        TODO("Not yet implemented")
    }

    override fun logout(userId: String): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun changePassword(
        params: ChangePasswordParams,
        userId: Long,
        token: String,
        timeStamp: String
    ): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun resetPassword(params: ResetPasswordParams): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun verifyNewEmail(params: VerifyNewEmailParams): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }
}
