package com.leevinapp.monitor

sealed class MainViewState {

    object FullScreen : MainViewState()
    object NavigationScreen : MainViewState()

    fun isFullScreen() = this is FullScreen
    fun isNavigationScreen() = this is NavigationScreen
}
