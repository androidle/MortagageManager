package com.leevinapp.monitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import javax.inject.Inject

val NAV_FRAGMENTS_ID = setOf(R.id.homeFragment, R.id.mineFragment, R.id.projectFragment, R.id.enterpriseFragment)

class MainViewModel @Inject constructor() : ViewModel() {
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
