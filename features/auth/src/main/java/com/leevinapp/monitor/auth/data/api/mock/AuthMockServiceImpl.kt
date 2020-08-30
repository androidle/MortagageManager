package com.leevinapp.monitor.auth.data.api.mock

import com.google.gson.reflect.TypeToken
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.TestResponse
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

    override fun auth(): Single<String> {
        TODO("Not yet implemented")
        // return null
    }
}
