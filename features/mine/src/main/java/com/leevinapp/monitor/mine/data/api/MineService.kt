package com.leevinapp.monitor.mine.data.api

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.data.response.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.response.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.response.VerifyOrganizationResponse
import com.leevinapp.monitor.mine.data.response.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.VerifyUserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Calendar

interface MineService {

    @GET("app/profile/user/{userId}")
    fun getUserProfile(
        @Path("userId") userId: String,
        @Header("X-AUTH-TOKEN") token: String,
        @Header("X-AUTH-TIMESTAMP") timeStamp: String = Calendar.getInstance().timeInMillis.toString()
    ): Single<GetUserProfileResponse>

    @POST("app/profile/user")
    fun updateUserProfile(@Body params: UpdateUserProfileParams): Single<ApiResponse<Any>>

    @POST("app/auth/verify/user")
    fun verifyUser(@Body params: VerifyUserParams): Single<VerifyUserResponse>

    @POST("app/auth/verify/organization")
    fun verifyOrganization(@Body params: VerifyOrganizationParams): Single<VerifyOrganizationResponse>
}
