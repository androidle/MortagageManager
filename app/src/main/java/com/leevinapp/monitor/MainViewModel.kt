package com.leevinapp.monitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.leevinapp.monitor.core.common.ui.base.BaseViewModel
import javax.inject.Inject

val NAV_FRAGMENTS_ID = setOf(R.id.homeFragment, R.id.mineFragment, R.id.projectFragment, R.id.enterpriseFragment)

class MainViewModel @Inject constructor() : BaseViewModel() {
    private val _state = MutableLiveData<MainViewState>()
    val state: LiveData<MainViewState>
        get() = _state

    // todo why not work on viewBinding
    fun navigationControllerChanged(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (NAV_FRAGMENTS_ID.contains(destination.id)) {
                _state.postValue(MainViewState.NavigationScreen)
            } else {
                _state.postValue(MainViewState.FullScreen)
            }
        }
    }
}
