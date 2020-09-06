package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import io.reactivex.Single

interface MineRepository {

    fun getUserProfile(userId: String): Single<GetUserProfileResponse>
}
