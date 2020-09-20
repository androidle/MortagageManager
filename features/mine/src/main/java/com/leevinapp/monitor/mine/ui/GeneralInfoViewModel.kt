package com.leevinapp.monitor.mine.ui

import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject
import timber.log.Timber

class GeneralInfoViewModel @Inject constructor(private val repository: PostAuthRepository) :
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
