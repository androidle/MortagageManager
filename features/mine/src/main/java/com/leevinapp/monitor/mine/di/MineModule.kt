package com.leevinapp.monitor.mine.di

import com.leevinapp.monitor.auth.data.PostAuthRepositoryImpl
import com.leevinapp.monitor.auth.data.api.PostAuthService
import com.leevinapp.monitor.auth.domain.PostAuthRepository
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.core.core.network.NetworkUtil
import com.leevinapp.monitor.core.core.network.qualifier.RealApi
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.mine.data.MineRepositoryImpl
import com.leevinapp.monitor.mine.data.api.MineService
import com.leevinapp.monitor.mine.domain.MineRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module(includes = [MineViewModelModule::class])
class MineModule {

    @Provides
    @FeatureScope
    fun providerRepository(@RealApi mineService: MineService): MineRepository {
        return MineRepositoryImpl(mineService)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerApiService(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient,
        userManager: UserManager
    ): MineService {
        val newRetrofit = NetworkUtil.postLogonRetrofit(retrofit, okHttpClient, userManager)
        return newRetrofit.create(MineService::class.java)
    }

    @Provides
    @FeatureScope
    fun providerAuthRepository(@RealApi authService: PostAuthService): PostAuthRepository {
        return PostAuthRepositoryImpl(authService)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerAuthService(retrofit: Retrofit, okHttpClient: OkHttpClient, userManager: UserManager): PostAuthService {
        val newRetrofit = NetworkUtil.postLogonRetrofit(retrofit, okHttpClient, userManager)
        return newRetrofit.create(PostAuthService::class.java)
    }
}
