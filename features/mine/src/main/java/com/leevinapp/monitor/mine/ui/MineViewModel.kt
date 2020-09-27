package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.domain.MineRepository
import javax.inject.Inject
import timber.log.Timber

class MineViewModel @Inject constructor(private val mineRepository: MineRepository, private val userManager: UserManager) :
    BaseViewModel() {

    init {
        Timber.d("MineViewModel init")
    }

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
}
