package com.leevinapp.monitor.auth.data

import com.leevinapp.monitor.auth.data.api.PostAuthService
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostAuthRepositoryImpl (
    private val postAuthService: PostAuthService,
    private val userManager: UserManager
): PostAuthRepository{

    override fun logout(): Single<ApiResponse<Any>> {
        return postAuthService.logout(userManager.user.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changePassword(changePasswordParams: ChangePasswordParams): Single<ApiResponse<Any>> {
        return postAuthService.changePassword(
            params = changePasswordParams
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun verifyNewEmail(params: VerifyNewEmailParams): Single<Boolean> {
        return postAuthService.verifyNewEmail(params)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}