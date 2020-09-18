package com.leevinapp.monitor.auth.domain

import com.leevinapp.monitor.auth.data.api.response.ChangePasswordParams
import com.leevinapp.monitor.auth.data.api.response.LoginResponse
import com.leevinapp.monitor.auth.data.api.response.RegisterUserParams
import com.leevinapp.monitor.auth.data.api.response.ResetPasswordParams
import com.leevinapp.monitor.auth.data.api.response.VerifyNewEmailParams
import io.reactivex.Single

interface AuthRepository {
    fun test(): Single<String>

    fun login(phoneNumber: String, password: String, smsCode: String): Single<LoginResponse>

    fun logout(userId: String, token: String): Single<Boolean>

    fun sendSmsCode(phoneNumber: String, smsType: String): Single<Boolean>

    fun sendEmailCode(email: String): Single<Boolean>

    fun registerUser(registerUserParams: RegisterUserParams): Single<String>

    fun changePassword(changePasswordParams: ChangePasswordParams): Single<Boolean>

    fun resetPassword(resetPasswordParams: ResetPasswordParams): Single<Boolean>

    fun verifyNewEmail(params: VerifyNewEmailParams): Single<Boolean>
}
