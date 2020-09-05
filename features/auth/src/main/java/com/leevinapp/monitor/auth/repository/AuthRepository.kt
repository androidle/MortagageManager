package com.leevinapp.monitor.auth.repository

import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.SendSmsCodeParams
import io.reactivex.Single

interface AuthRepository {
    fun test(): Single<String>

    fun login(phoneNumber: String, password: String, smsCode: String): Single<String>

    fun sendSmsCode(phoneNumber: SendSmsCodeParams):Single<Boolean>

    fun registerUser(registerUserParams: RegisterUserParams):Single<String>
}
