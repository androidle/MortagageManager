package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.data.response.GetTicketInfoResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import timber.log.Timber

class MineViewModel @Inject constructor(private val repository: MineRepository, private val userManager: UserManager) :
    BaseViewModel() {

    var refreshData = true

    init {
        Timber.d("MineViewModel init")
    }

    val ticketInfoResult = MutableLiveData<GetTicketInfoResponse>()

    val isLogged = MutableLiveData<Boolean>(false)

    val userRole = MediatorLiveData<String>().apply {
        addSource(isLogged) {
            value = if (isLogged.value == true) {
                userManager.user.role?.name
            } else {
                "点击头像登录"
            }
        }
    }

    val username = MediatorLiveData<String>().apply {
        addSource(isLogged) {
            value = if (isLogged.value == true) {
                userManager.user.fullName
            } else {
                "未登录"
            }
        }
    }

    fun getTicketInfo() {
        if (refreshData.not()) return
        repository.getTicketInfo()
            .applyIoWithLoading()
            .subscribe { t ->
                if (t.success.not()) {
                    errorMessage.value = t.error
                } else {
                    refreshData = false
                    ticketInfoResult.value = t.data
                }
            }
            .addTo(compositeDisposable)
    }
}
