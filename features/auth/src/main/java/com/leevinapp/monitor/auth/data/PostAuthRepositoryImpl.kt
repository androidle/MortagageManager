package com.leevinapp.monitor.auth.data

import com.leevinapp.monitor.auth.data.api.PostAuthService
import com.leevinapp.monitor.auth.data.api.params.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.params.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import io.reactivex.Single

class PostAuthRepositoryImpl(
    private val postAuthService: PostAuthService,
    private val userManager: UserManager
) : PostAuthRepository {

    override fun logout(): Single<ApiResponse<Any>> {
        return postAuthService.logout(userManager.user.id)
    }

    override fun changePassword(changePasswordParams: ChangePasswordParams): Single<ApiResponse<Any>> {
        return postAuthService.changePassword(params = changePasswordParams)
    }

    override fun verifyNewEmail(params: VerifyNewEmailParams): Single<ApiResponse<Any>> {
        return postAuthService.verifyNewEmail(params)
    }

    override fun sendEmailVerifyCode(email: String): Single<ApiResponse<Any>> {
        return postAuthService.sendEmailVerifyCode(email)
    }
}
