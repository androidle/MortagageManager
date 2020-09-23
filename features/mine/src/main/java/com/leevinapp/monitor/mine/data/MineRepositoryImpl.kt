package com.leevinapp.monitor.mine.data

import com.leevinapp.monitor.core.core.network.ApiResponse
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.data.api.MineService
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
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MineRepositoryImpl(
    private val mineService: MineService,
    private val userManager: UserManager
) : MineRepository {
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
        return mineService.searchInstitution(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun verifyUser(params: VerifyUserParams): Single<ApiResponse<Any>> {
        return mineService.verifyUser(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun verifyOrganization(params: VerifyOrganizationParams): Single<ApiResponse<Any>> {
        return mineService.verifyOrganization(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSubInstitution(): Single<ApiResponse<GetSubInstitutionResponse>> {
        return mineService.getSubInstitution()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTickets(params: GetTicketsParams): Single<ApiResponse<List<GetTicketDetailsResponse>>> {
        return mineService.getTickets(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun approveTicketByOrgan(params: GetTicketsParams): Single<ApiResponse<Any>> {
        return mineService.approveTicketByOrgan(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun rejectTicketByOrgan(params: RejectTicketParams): Single<ApiResponse<Any>> {
        return mineService.rejectTicketByOrgan(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun approveTicketByPlatform(params: RejectTicketParams): Single<ApiResponse<Any>> {
        return mineService.approveTicketByPlatform(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun rejectTicketByPlatform(params: RejectTicketParams): Single<ApiResponse<Any>> {
        return mineService.rejectTicketByPlatform(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTicketDetails(ticketId: String): Single<ApiResponse<GetTicketDetailsResponse>> {
        return mineService.getTicketDetails(ticketId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNotifications(userRole: String): Single<ApiResponse<GetNotificationsResponse>> {
        return mineService.getNotifications(userRole)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun requestTicket(
        userRole: String,
        paramsRequest: RequestTicketParams
    ): Single<ApiResponse<RaiseTicketResponse>> {
        return mineService.requestTicket(userRole, paramsRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTicketOrNotificationQuantity(): Single<ApiResponse<Any>> {
        return mineService.getTicketOrNotificationQuantity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchUser(keyword: String): Single<ApiResponse<GetUserProfileResponse>> {
        return mineService.getUser(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUsersInOrg(): Single<ApiResponse<GetUserProfileResponse>> {
        return mineService.getUsersInOrg()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTicketInfo(userRole: String): Single<ApiResponse<GetTicketInfoResponse>> {
        return mineService.getTicketInfo(userRole)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
