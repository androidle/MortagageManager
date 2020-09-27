package com.leevinapp.monitor.mine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import com.leevinapp.monitor.mine.data.params.RequestTicketParams
import com.leevinapp.monitor.mine.domain.MineRepository
import io.reactivex.rxkotlin.addTo
import java.util.Locale

abstract class ApplyTicketViewModel(private val repository: MineRepository) :
    BaseViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    val requestDesc = MutableLiveData("")

    val requestTicketResult = MutableLiveData(false)

    fun requestTicket() {
        repository.requestTicket(
            requestTicketParams()
        )
            .applyIoWithLoading()
            .subscribe({ t ->
                requestTicketResult.postValue(t.success)
                if (t.success.not()) {
                    errorMessage.postValue(t.error)
                }
            }, {})
            .addTo(compositeDisposable)
    }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == _query.value) {
            return
        }
        _query.value = input
    }

    abstract fun requestTicketParams(): RequestTicketParams
}
