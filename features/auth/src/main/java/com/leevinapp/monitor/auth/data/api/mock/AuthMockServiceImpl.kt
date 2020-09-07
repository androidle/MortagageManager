package com.leevinapp.monitor.auth.data.api.mock

import com.google.gson.reflect.TypeToken
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserResponse
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeResponse
import com.leevinapp.monitor.auth.data.api.response.TestResponse
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import io.reactivex.Single
import timber.log.Timber

class AuthMockServiceImpl(private val mockApiUtil: MockApiUtil) :
    AuthService {

    override fun test(): Single<TestResponse> {
        val mockResponse = mockApiUtil.getMockResponse<TestResponse>(
            "test.json",
            object : TypeToken<TestResponse>() {}.type
        )

        Timber.d(TAG_MOCK_API + mockResponse)
        return Single.just(mockResponse)
    }

    companion object {
        const val TAG_MOCK_API = "Mock response=>:"
    }

    override fun login(params: LoginParams): Single<ApiResponse<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override fun logout(params: LoginParams): Single<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun sendSmsCode(params: SendSmsCodeParams): Single<SendSmsCodeResponse> {
        TODO("Not yet implemented")
    }

    override fun registerUser(params: RegisterUserParams): Single<ApiResponse<RegisterUserResponse>> {
        TODO("Not yet implemented")
    }

    override fun logout(userId: String, token: String): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }

    override fun changePassword(params: ChangePasswordParams): Single<ApiResponse<Any>> {
        TODO("Not yet implemented")
    }
}
