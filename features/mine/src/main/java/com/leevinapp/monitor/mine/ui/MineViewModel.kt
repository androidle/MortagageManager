package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.mine.domain.MineRepository
import javax.inject.Inject
import timber.log.Timber

class MineViewModel @Inject constructor(private val mineRepository: MineRepository) : ViewModel() {

    init {
        Timber.d("MineViewModel init")
    }

    val isLogged = MutableLiveData<Boolean>(false)

    val username = MediatorLiveData<String>().apply {
        addSource(isLogged) {
            value = if (isLogged.value == true) {
                "Hello world"
            } else {
                "未登录"
            }
        }
    }
}
