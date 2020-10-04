package com.leevinapp.monitor.auth.di

import androidx.fragment.app.Fragment
import com.leevinapp.monitor.auth.data.AuthRepositoryImpl
import com.leevinapp.monitor.auth.data.PostAuthRepositoryImpl
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.data.api.PostAuthService
import com.leevinapp.monitor.auth.data.api.mock.AuthMockServiceImpl
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.core.core.network.NetworkUtil
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.core.core.network.qualifier.MockApi
import com.leevinapp.monitor.core.core.network.qualifier.RealApi
import com.leevinapp.monitor.core.core.user.UserManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module(includes = [AuthViewModelModule::class])
class AuthModule(val fragment: Fragment) {

    @Provides
    @FeatureScope
    fun providerAuthRepository(
        @RealApi authService: AuthService,
        userManager: UserManager
    ): AuthRepository {
        return AuthRepositoryImpl(authService, userManager)
    }

    @Provides
    @FeatureScope
    fun providerPostAuthRepository(@RealApi authService: PostAuthService): PostAuthRepository {
        return PostAuthRepositoryImpl(authService)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerAuthApiService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerPostApiService(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient,
        userManager: UserManager
    ): PostAuthService {
        val postLogonRetrofit = NetworkUtil.postLogonRetrofit(retrofit, okHttpClient, userManager)
        return postLogonRetrofit.create(PostAuthService::class.java)
    }

    @MockApi
    @FeatureScope
    @Provides
    fun authMockApiService(mockApiUtil: MockApiUtil): AuthService {
        return AuthMockServiceImpl(mockApiUtil)
    }
}
