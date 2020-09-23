package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.GetTicketsParams
import com.leevinapp.monitor.mine.data.params.RejectTicketParams
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.params.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.params.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.GetTicketDetailsResponse
import com.leevinapp.monitor.mine.data.response.GetTicketInfoResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.data.response.RaiseTicketResponse
import io.reactivex.Single

interface MineRepository {

    fun getUserProfile(): Single<ApiResponse<GetUserProfileResponse>>

    fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>>

    fun searchInstitution(search: String): Single<ApiResponse<GetSubInstitutionResponse>>

    fun verifyUser(params: VerifyUserParams): Single<ApiResponse<Any>>

    fun verifyOrganization(params: VerifyOrganizationParams): Single<ApiResponse<Any>>

    fun getSubInstitution(): Single<ApiResponse<GetSubInstitutionResponse>>

    fun getTickets(params: GetTicketsParams): Single<ApiResponse<List<GetTicketDetailsResponse>>>

    fun getTicketDetails(ticketId: String): Single<ApiResponse<GetTicketDetailsResponse>>

    fun approveTicketByOrgan(params: GetTicketsParams): Single<ApiResponse<Any>>

    fun rejectTicketByOrgan(params: RejectTicketParams): Single<ApiResponse<Any>>

    fun approveTicketByPlatform(params: RejectTicketParams): Single<ApiResponse<Any>>

    fun rejectTicketByPlatform(params: RejectTicketParams): Single<ApiResponse<Any>>

    fun getNotifications(userRole: String): Single<ApiResponse<GetNotificationsResponse>>

    fun requestTicket(
        userRole: String,
        paramsRequest: RequestTicketParams
    ): Single<ApiResponse<RaiseTicketResponse>>

    fun getTicketOrNotificationQuantity(): Single<ApiResponse<Any>>

    fun searchUser(keyword: String): Single<ApiResponse<GetUserProfileResponse>>

    fun getUsersInOrg(): Single<ApiResponse<GetUserProfileResponse>>

    fun getTicketInfo(userRole: String): Single<ApiResponse<GetTicketInfoResponse>>
}
