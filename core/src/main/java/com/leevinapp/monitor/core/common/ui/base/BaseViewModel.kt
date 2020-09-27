package com.leevinapp.monitor.core.common.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.core.core.network.exception.ExceptionHandler
import com.leevinapp.monitor.core.core.network.exception.ExceptionHandlerImpl
import com.leevinapp.monitor.core.core.network.exception.ResponseException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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

    protected fun <T> Single<T>.applyIoWithLoading(): Single<T> {
        return doOnSubscribe { loading.postValue(true) }
            .doFinally { loading.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Timber.e("==doOnError==>${it.cause} \n ${it.message}")
                doOnException(exceptionHandler.handleException(it))
            }
            .doOnSuccess { Timber.d("==doOnSuccess==>$it") }
    }

    protected fun <T> Single<T>.applyIoWithoutLoading(): Single<T> {
        return doOnError {
                Timber.e("==doOnError==>${it.cause} \n ${it.message}")
                doOnException(exceptionHandler.handleException(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { Timber.d("==doOnSuccess==>$it") }
    }

    private fun doOnException(exception: ResponseException) {
        handleCommonException(exception)
    }

    open fun handleCommonException(exception: ResponseException) {
        Timber.e("==afterHandler==>${exception.message}")
        errorMessage.postValue(exception.message)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
