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
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val exceptionHandler: ExceptionHandler by lazy {
        ExceptionHandlerImpl()
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData("")
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
        Timber.e("====>${exception.message}")
        errorMessage.postValue(exception.message)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
