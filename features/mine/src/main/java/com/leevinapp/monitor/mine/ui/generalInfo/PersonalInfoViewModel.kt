package com.leevinapp.monitor.mine.ui.generalInfo

import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.response.GetUserProfileResponse
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import timber.log.Timber

class PersonalInfoViewModel @Inject constructor(private val mineRepository: MineRepository) : BaseViewModel() {

    fun getUserProfile(userId: String) {
        mineRepository.getUserProfile(userId)
            .applyIoSchedules()
            .subscribe(object : SingleObserver<GetUserProfileResponse> {
                override fun onSuccess(t: GetUserProfileResponse) {
                    Timber.d("==>$t")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Timber.d("==>${e.message}")
                }
            })
    }
}
