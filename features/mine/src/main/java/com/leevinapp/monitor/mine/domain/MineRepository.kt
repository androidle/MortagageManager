package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import io.reactivex.Single

interface MineRepository {

    fun getUserProfile(): Single<GetUserProfileResponse>

    fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>>
}
