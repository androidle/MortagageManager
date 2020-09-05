package com.leevinapp.monitor.auth.data

import com.google.gson.Gson
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.response.LoginParams
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import com.leevinapp.monitor.auth.repository.AuthRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
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
    ): Single<String> {
        return authService.login(LoginParams(telephone = phoneNumber,password = password,smsVerifyCode = smsCode))
            .map {
                it.data.token
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendSmsCode(params: SendSmsCodeParams): Single<Boolean> {
        // todo
        return authService.sendSmsCode(params)
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
}
