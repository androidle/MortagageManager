package com.leevinapp.monitor.mine.ui

import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class GeneralInfoViewModel @Inject constructor(private val repository: PostAuthRepository) :
    BaseViewModel() {

    fun logout() {
        repository.logout()
            .applyIoWithLoading()
            .subscribe()
            .addTo(compositeDisposable)
    }
}
