package com.leevinapp.monitor.mine.domain

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.params.ApproveTicketParams
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.data.params.UpdateUserProfileParams
import com.leevinapp.monitor.mine.data.params.VerifyOrganizationParams
import com.leevinapp.monitor.mine.data.params.VerifyUserParams
import com.leevinapp.monitor.mine.data.response.GetNotificationsResponse
import com.leevinapp.monitor.mine.data.response.GetSubInstitutionResponse
import com.leevinapp.monitor.mine.data.response.GetTicketDetailsResponse
import com.leevinapp.monitor.mine.data.response.GetTicketInfoResponse
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.data.response.RequestTicketResponse
import io.reactivex.Single

interface MineRepository {

    fun getUserProfile(): Single<ApiResponse<GetUserProfileResponse>>

    fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>>

    fun searchInstitution(search: String): Single<ApiResponse<List<GetSubInstitutionResponse>>>

    fun verifyUser(params: VerifyUserParams): Single<ApiResponse<Any>>

    fun verifyOrganization(params: VerifyOrganizationParams): Single<ApiResponse<Any>>

    fun getSubInstitution(): Single<ApiResponse<List<GetSubInstitutionResponse>>>

    fun getTickets(status: String): Single<ApiResponse<List<GetTicketDetailsResponse>>>

    fun getTicketDetails(ticketId: String): Single<ApiResponse<GetTicketDetailsResponse>>

    fun approveTicket(params: ApproveTicketParams): Single<ApiResponse<Any>>

    fun getNotifications(): Single<ApiResponse<List<GetNotificationsResponse>>>

    fun requestTicket(paramsRequest: RequestTicketParams): Single<ApiResponse<RequestTicketResponse>>

    fun getTicketOrNotificationQuantity(): Single<ApiResponse<Any>>

    fun searchUser(keyword: String): Single<ApiResponse<List<GetUserProfileResponse>>>

    fun getUsersInOrg(): Single<ApiResponse<List<GetUserProfileResponse>>>

    fun getTicketInfo(): Single<ApiResponse<GetTicketInfoResponse>>
}
