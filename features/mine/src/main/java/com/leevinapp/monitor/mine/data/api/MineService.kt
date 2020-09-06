package com.leevinapp.monitor.mine.data.api

import com.leevinapp.monitor.core.core.network.BaseResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.data.response.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.response.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.response.VerifyOrganizationResponse
import com.leevinapp.monitor.mine.data.response.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.VerifyUserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MineService {

    @GET("app/profile/user/{userId}")
    fun getUserProfile(@Path("userId") userId: String): Single<GetUserProfileResponse>

    @POST("app/profile/user")
    fun updateUserProfile(@Body params: UpdateUserProfileParams): Single<BaseResponse<Any>>

    @POST("app/auth/verify/user")
    fun verifyUser(@Body params: VerifyUserParams): Single<VerifyUserResponse>

    @POST("app/auth/verify/organization")
    fun verifyOrganization(@Body params: VerifyOrganizationParams): Single<VerifyOrganizationResponse>
}
