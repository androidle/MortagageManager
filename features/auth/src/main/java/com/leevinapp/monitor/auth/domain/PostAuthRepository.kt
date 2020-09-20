package com.leevinapp.monitor.auth.domain

import com.leevinapp.monitor.auth.data.api.params.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.params.VerifyNewEmailParams
import com.leevinapp.monitor.core.core.network.ApiResponse
import io.reactivex.Single

interface PostAuthRepository {
    fun logout(): Single<ApiResponse<Any>>

    fun changePassword(changePasswordParams: ChangePasswordParams): Single<ApiResponse<Any>>

    fun verifyNewEmail(params: VerifyNewEmailParams): Single<ApiResponse<Any>>
}
