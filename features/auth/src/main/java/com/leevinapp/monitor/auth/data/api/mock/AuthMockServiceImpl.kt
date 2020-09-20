package com.leevinapp.monitor.auth.data.api.mock

import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.params.LoginParams
import com.leevinapp.monitor.auth.data.api.params.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.params.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.params.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
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

    override fun sendSmsCode(params: SendSmsCodeParams): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun sendEmailVerifyCode(email: String): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun registerUser(params: RegisterUserParams): Single<ApiResponse<RegisterUserResponse>> {
        TODO("Not yet implemented")
    }

    override fun resetPassword(params: ResetPasswordParams): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }
}
