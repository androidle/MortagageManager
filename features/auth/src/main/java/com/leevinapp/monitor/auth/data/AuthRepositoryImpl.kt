package com.leevinapp.monitor.auth.data

import com.google.gson.Gson
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.model.AuthModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepositoryImpl(private val authService: AuthService) :
    AuthRepository {
    override fun test(): Single<String> {
        return authService.test()
            .map {
                // TODO: 2020/8/29
                Gson().toJson(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun login(
        phoneNumber: String,
        password: String,
        smsCode: String
    ): Single<AuthModel> {
        return authService.login(LoginParams(telephone = phoneNumber, password = password, smsVerifyCode = smsCode))
            .map {
                AuthModel(
                    token = it.data.token,
                    role = it.data.role,
                    userFullName = it.data.userFullName,
                    telephone = it.data.telephone
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendSmsCode(phoneNumber: String, smsType: String): Single<Boolean> {
        return authService.sendSmsCode(SendSmsCodeParams(telephone = phoneNumber, smsType = smsType))
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

    override fun logout(userId: String, token: String): Single<Boolean> {
        return authService.logout(userId, token)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changePassword(changePasswordParams: ChangePasswordParams): Single<Boolean> {
        return authService.changePassword(params = changePasswordParams)
            .map { it.success }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
