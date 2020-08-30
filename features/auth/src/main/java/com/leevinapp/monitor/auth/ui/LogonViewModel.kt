package com.leevinapp.monitor.auth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leevinapp.monitor.auth.repository.AuthRepository
import io.reactivex.functions.Consumer
import javax.inject.Inject
import timber.log.Timber

class LogonViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }
    fun login() {
        authRepository.test()
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }

    fun auth() {
        authRepository.auth()
            .doOnSubscribe {
                loading.postValue(true)
            }
            .doFinally {
                loading.postValue(false)
            }
            .subscribe(Consumer {
                Timber.d("====>$it")
            }, Consumer {
                Timber.e("====>$it")
            })
    }

    val username = MutableLiveData("hello")
}