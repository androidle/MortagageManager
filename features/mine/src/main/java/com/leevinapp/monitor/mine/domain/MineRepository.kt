package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.params.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.params.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import io.reactivex.Single

interface MineRepository {

    fun getUserProfile(): Single<ApiResponse<GetUserProfileResponse>>

    fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>>

    fun searchInstitution(search: String): Single<ApiResponse<GetSubInstitutionResponse>>

    fun verifyUser(params: VerifyUserParams): Single<ApiResponse<Any>>

    fun verifyOrganization(params: VerifyOrganizationParams): Single<ApiResponse<Any>>
}
