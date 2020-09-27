package com.leevinapp.monitor.mine.data

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.mine.data.api.MineService
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
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.Single

class MineRepositoryImpl(
    private val mineService: MineService
) : MineRepository {
    override fun getUserProfile(): Single<ApiResponse<GetUserProfileResponse>> {
        return mineService.getUserProfile()
    }

    override fun updateUserProfile(params: UpdateUserProfileParams): Single<ApiResponse<Any>> {
        return mineService.updateUserProfile(params)
    }

    override fun searchInstitution(search: String): Single<ApiResponse<List<GetSubInstitutionResponse>>> {
        return mineService.searchInstitution(search)
    }

    override fun verifyUser(params: VerifyUserParams): Single<ApiResponse<Any>> {
        return mineService.verifyUser(params)
    }

    override fun verifyOrganization(params: VerifyOrganizationParams): Single<ApiResponse<Any>> {
        return mineService.verifyOrganization(params)
    }

    override fun getSubInstitution(): Single<ApiResponse<List<GetSubInstitutionResponse>>> {
        return mineService.getSubInstitution()
    }

    override fun getTickets(status: String): Single<ApiResponse<List<GetTicketDetailsResponse>>> {
        return mineService.getTickets(status)
    }

    override fun approveTicket(params: ApproveTicketParams): Single<ApiResponse<Any>> {
        return mineService.approveTicket(params)
    }

    override fun getTicketDetails(ticketId: String): Single<ApiResponse<GetTicketDetailsResponse>> {
        return mineService.getTicketDetails(ticketId)
    }

    override fun getNotifications(): Single<ApiResponse<List<GetNotificationsResponse>>> {
        return mineService.getNotifications()
    }

    override fun requestTicket(paramsRequest: RequestTicketParams): Single<ApiResponse<RequestTicketResponse>> {
        return mineService.requestTicket(paramsRequest)
    }

    override fun getTicketOrNotificationQuantity(): Single<ApiResponse<Any>> {
        return mineService.getTicketOrNotificationQuantity()
    }

    override fun searchUser(keyword: String): Single<ApiResponse<List<GetUserProfileResponse>>> {
        return mineService.searchUser(keyword)
    }

    override fun getUsersInOrg(): Single<ApiResponse<List<GetUserProfileResponse>>> {
        return mineService.getUsersInOrg()
    }

    override fun getTicketInfo(): Single<ApiResponse<GetTicketInfoResponse>> {
        return mineService.getTicketInfo()
    }
}
