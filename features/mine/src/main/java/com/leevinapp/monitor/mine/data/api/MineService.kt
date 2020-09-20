package com.leevinapp.monitor.mine.data.api

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.GetTicketsParams
import com.leevinapp.monitor.mine.data.params.RaiseTicketParams
import com.leevinapp.monitor.mine.data.params.RejectTicketParams
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.params.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.params.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.data.response.RaiseTicketResponse
import com.leevinapp.monitor.mine.data.response.VerifyOrganizationResponse
import com.leevinapp.monitor.mine.data.response.VerifyUserResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MineService {

    @GET("app/profile/user")
    fun getUserProfile(): Single<GetUserProfileResponse>

    @POST("app/profile/user")
    fun updateUserProfile(@Body params: UpdateUserProfileParams): Single<ApiResponse<Any>>

    @POST("app/auth/verify/user")
    fun verifyUser(@Body params: VerifyUserParams): Single<ApiResponse<VerifyUserResponse>>

    @POST("app/auth/verify/organization")
    fun verifyOrganization(@Body params: VerifyOrganizationParams): Single<ApiResponse<VerifyOrganizationResponse>>

    @GET("app/notifyList")
    fun getNotifications(@Header("user_role") userRole: String): Single<ApiResponse<GetNotificationsResponse>>

    @GET("app/org/subOrg")
    fun getSubInstitution(): Single<ApiResponse<GetSubInstitutionResponse>>

    @GET("app/org/list/search")
    fun getOrg(@Query("keyword")keyword: String): Single<ApiResponse<GetSubInstitutionResponse>>

    @POST("app/ticket/request")
    fun raiseTicket(@Header("user_role") userRole: String, @Body paramsRaise: RaiseTicketParams):
        Single<ApiResponse<RaiseTicketResponse>>

    @GET("app/ticket/ticketOrNotifyQty")
    fun getTicketOrNotificationQuantity(): Single<ApiResponse<Any>>

    @GET("app/user/list/search")
    fun getUser(@Query("keyword")keyword: String): Single<ApiResponse<GetUserProfileResponse>>

    @GET("app/userInOrg")
    fun getUsersInOrg(): Single<ApiResponse<GetUserProfileResponse>>

    @GET("common/ticket/details")
    fun getTicketDetails(@Query("ticketId") ticketId: String): Single<ApiResponse<Any>>

    @POST("common/ticket/list")
    fun getTickets(@Body params: GetTicketsParams): Single<ApiResponse<Any>>

    @POST("common/ticket/orgAdmin/approve")
    fun approveTicket(@Body params: GetTicketsParams): Single<ApiResponse<Any>>

    @POST("common/ticket/orgAdmin/approve")
    fun rejectTicket(@Body params: RejectTicketParams): Single<ApiResponse<Any>>
}
