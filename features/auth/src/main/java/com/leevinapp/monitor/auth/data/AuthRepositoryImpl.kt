package com.leevinapp.monitor.auth.data

import com.google.gson.Gson
import com.leevinapp.monitor.auth.data.api.AuthService
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

    override fun auth(): Single<String> {
        return authService.auth()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
