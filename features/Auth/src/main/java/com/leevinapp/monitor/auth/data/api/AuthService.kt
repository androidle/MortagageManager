package com.leevinapp.monitor.auth.data.api

import com.leevinapp.monitor.auth.data.api.response.TestResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AuthService {
    @GET("users/list")
    fun test(): Single<TestResponse>
}
