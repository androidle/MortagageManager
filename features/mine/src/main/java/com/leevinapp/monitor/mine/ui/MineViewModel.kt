package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.domain.MineRepository
import timber.log.Timber
import javax.inject.Inject

class MineViewModel @Inject constructor(private val mineRepository: MineRepository,private val userManager: UserManager) : ViewModel() {

    init {
        Timber.d("MineViewModel init")
    }

    val isLogged = MutableLiveData<Boolean>(false)

    val userRole = MediatorLiveData<String>().apply {
        addSource(isLogged) {
            value = if (isLogged.value == true) {
                userManager.user.role
            } else {
                "点击头像登录"
            }
        }
    }

    val username = MediatorLiveData<String>().apply {
        addSource(isLogged) {
            value = if (isLogged.value == true) {
                userManager.user.fullname
            } else {
                "未登录"
            }
        }
    }
}
