package com.leevinapp.monitor.mine.data

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.data.api.MineService
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MineRepositoryImpl(private val mineService: MineService, private val userManager: UserManager) : MineRepository {
    override fun getUserProfile(): Single<ApiResponse<GetUserProfileResponse>> {
        return mineService.getUserProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>> {
        return mineService.updateUserProfile(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchInstitution(search: String): Single<ApiResponse<GetSubInstitutionResponse>> {
        return mineService.searchOrg(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
