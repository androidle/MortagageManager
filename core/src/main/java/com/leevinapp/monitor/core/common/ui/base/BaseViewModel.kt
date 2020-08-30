package com.leevinapp.monitor.core.common.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.core.core.network.exception.ExceptionHandler
import com.leevinapp.monitor.core.core.network.exception.ExceptionHandlerImpl
import com.leevinapp.monitor.core.core.network.exception.ResponseException
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    private val exceptionHandler: ExceptionHandler by lazy {
        ExceptionHandlerImpl()
    }

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    protected fun <T> Single<T>.applyIoSchedules(): Single<T> {
        return subscribeOn(Schedulers.io())
            .doOnSubscribe { loading.postValue(true) }
            .doOnError { doOnException(exceptionHandler.handleException(it)) }
            .doFinally { loading.postValue(false) }
    }

    protected fun <T> Observable<T>.applyIoSchedules(): Observable<T> {
        return subscribeOn(Schedulers.io())
            .doOnSubscribe { loading.postValue(true) }
            .doOnError { doOnException(exceptionHandler.handleException(it)) }
            .doFinally { loading.postValue(false) }
    }

    private fun doOnException(exception: ResponseException) {
        handleCommonException(exception)
    }

    open fun handleCommonException(exception: ResponseException) {
        // TODO: 2020/8/29
    }

    override fun onCleared() {
        super.onCleared()
    }
}
