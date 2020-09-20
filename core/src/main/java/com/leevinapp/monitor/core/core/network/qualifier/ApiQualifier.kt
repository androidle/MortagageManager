package com.leevinapp.monitor.core.core.network.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealApi
