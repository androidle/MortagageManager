package com.leevinapp.monitor.mine.di

import com.leevinapp.monitor.auth.data.AuthRepositoryImpl
import com.leevinapp.monitor.auth.data.api.AuthService
import com.leevinapp.monitor.auth.domain.AuthRepository
import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.core.core.network.interceptor.PostLogonHeaderInterceptor
import com.leevinapp.monitor.core.core.network.mock.MockApi
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.core.core.network.mock.RealApi
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
    fun providerRepository(@RealApi mineService: MineService, userManager: UserManager): MineRepository {
        return MineRepositoryImpl(mineService, userManager)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerApiService(retrofit: Retrofit): MineService {
        return retrofit.create(MineService::class.java)
    }


    @Provides
    @FeatureScope
    fun providerAuthRepository(@RealApi authService: AuthService,userManager: UserManager): AuthRepository {
        return AuthRepositoryImpl(authService,userManager)
    }

    @RealApi
    @FeatureScope
    @Provides
    fun providerAuthService(retrofit: Retrofit,okHttpClient: OkHttpClient,userManager: UserManager): AuthService {
        val postLogonHeaderInterceptor = object : PostLogonHeaderInterceptor() {
            override fun getToken(): String {
                return userManager.token
            }
        }
        val newClientBuilder = okHttpClient.newBuilder()
        newClientBuilder
            .interceptors()
            .add(0, postLogonHeaderInterceptor)

        val newRetrofit = retrofit
            .newBuilder()
            .client(newClientBuilder.build())
            .build()
        return newRetrofit.create(AuthService::class.java)
    }



    @MockApi
    @FeatureScope
    @Provides
    fun authMockApiService(mockApiUtil: MockApiUtil): AuthService {
        TODO()
    }
}
