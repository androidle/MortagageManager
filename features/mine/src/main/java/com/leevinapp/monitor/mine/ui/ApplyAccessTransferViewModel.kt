package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.core.user.UserModel
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import com.leevinapp.monitor.mine.domain.model.TicketType.PERMISSION_TRANSFER
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ApplyAccessTransferViewModel @Inject constructor(private val repository: MineRepository) :
    ApplyTicketViewModel(repository) {

    val targetUser = MutableLiveData(UserModel())
    val userListResult = MutableLiveData<MutableList<UserModel>>()

    override fun requestTicketParams(): RequestTicketParams {
        return RequestTicketParams(
            desc = requestDesc.value ?: "",
            targetId = targetUser.value?.id ?: 0,
            type = PERMISSION_TRANSFER
        )
    }

    fun getSearchUser() {
        repository.searchUser(query.value ?: "")
            .applyIoWithLoading()
            .subscribe(Consumer {
                if (it.success) {
                    userListResult.postValue(toModel(it.data))
                } else {
                    errorMessage.postValue(it.error)
                }
            }).addTo(compositeDisposable)
    }

    fun targetUser(it: UserModel) {
        targetUser.value = it
    }

    private fun toModel(response: List<GetUserProfileResponse>): MutableList<UserModel>? {
        val mutableListOf = mutableListOf<UserModel>()
        response.forEach { data ->
            mutableListOf.add(
                data.toModel()
            )
        }
        return mutableListOf
    }
}
