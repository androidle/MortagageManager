package com.leevinapp.monitor.auth.data

import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val userManager: UserManager
) :
    AuthRepository {

    override fun login(
        phoneNumber: String,
        password: String,
        smsCode: String
    ): Single<LoginResponse> {
        return authService.login(
            LoginParams(
                telephone = phoneNumber,
                password = password,
                smsVerifyCode = smsCode
            )
        )
            .map {
                it.data
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendSmsCode(phoneNumber: String, smsType: String): Single<Boolean> {
        return authService.sendSmsCode(
            SendSmsCodeParams(
                telephone = phoneNumber,
                smsType = smsType
            )
        )
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendEmailCode(email: String): Single<Boolean> {
        return authService.sendEmailVerifyCode(email)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun registerUser(registerUserParams: RegisterUserParams): Single<String> {
        return authService.registerUser(registerUserParams)
            .map { it.data.token }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun logout(): Single<ApiResponse<Any>> {
        return authService.logout(userManager.user.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changePassword(changePasswordParams: ChangePasswordParams): Single<ApiResponse<Any>> {
        return authService.changePassword(
            params = changePasswordParams,
            userId = userManager.user.userId,
            token = userManager.token
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun resetPassword(resetPasswordParams: ResetPasswordParams): Single<Boolean> {
        return authService.resetPassword(params = resetPasswordParams)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun verifyNewEmail(params: VerifyNewEmailParams): Single<Boolean> {
        return authService.verifyNewEmail(params)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
