package com.leevinapp.monitor.auth.repository

import io.reactivex.Single

interface AuthRepository {
    fun test(): Single<String>
}
