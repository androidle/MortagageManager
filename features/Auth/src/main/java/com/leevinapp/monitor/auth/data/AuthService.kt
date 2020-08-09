package com.leevinapp.monitor.auth.data

import io.reactivex.Single
import retrofit2.http.GET

interface AuthService {
    @GET("users/list")
    fun test(): Single<Any>
}