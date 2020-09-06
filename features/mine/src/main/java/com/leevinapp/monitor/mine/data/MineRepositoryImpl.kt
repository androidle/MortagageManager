package com.leevinapp.monitor.mine.data

import com.leevinapp.monitor.mine.data.api.MineService
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.Single

class MineRepositoryImpl(private val mineService: MineService) : MineRepository {
    override fun getUserProfile(userId: String): Single<GetUserProfileResponse> {
        TODO("Not yet implemented")
    }
}
