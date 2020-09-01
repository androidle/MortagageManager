package com.leevinapp.monitor.auth.di

import androidx.fragment.app.Fragment
import com.leevinapp.monitor.auth.data.AuthRepositoryImpl
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.mock.AuthMockServiceImpl
import com.leevinapp.monitor.auth.repository.AuthRepository
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.core.core.network.mock.MockApi
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.core.core.network.mock.RealApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [AuthViewModelModule::class])
class AuthModule(val fragment: Fragment) {

    @Provides
    @FeatureScope
    fun providerRepository(@RealApi authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerApiService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @MockApi
    @FeatureScope
    @Provides
    fun authMockApiService(mockApiUtil: MockApiUtil): AuthService {
        return AuthMockServiceImpl(mockApiUtil)
    }
}
