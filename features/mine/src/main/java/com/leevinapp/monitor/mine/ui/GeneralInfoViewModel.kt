package com.leevinapp.monitor.mine.ui

import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class GeneralInfoViewModel @Inject constructor(private val repository: AuthRepository) :
    BaseViewModel() {

    fun logout() {
        repository.logout()
            .applyIoSchedules()
            .subscribe(Consumer {
                Timber.d("====>${it.success}  ${it.error}")
            }, Consumer {
                Timber.e("====>$it")
            })
    }
}