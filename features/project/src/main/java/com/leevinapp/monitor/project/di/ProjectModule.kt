package com.leevinapp.monitor.project.di

import com.leevinapp.monitor.core.core.di.scopes.FeatureScope
import com.leevinapp.monitor.core.core.network.NetworkUtil
import com.leevinapp.monitor.core.core.network.mock.MockApiUtil
import com.leevinapp.monitor.core.core.network.qualifier.MockApi
import com.leevinapp.monitor.core.core.network.qualifier.RealApi
import com.leevinapp.monitor.core.core.user.UserManager
import com.leevinapp.monitor.project.data.ProjectRepositoryImpl
import com.leevinapp.monitor.project.data.api.ProjectService
import com.leevinapp.monitor.project.data.mock.ProjectMockServiceImpl
import com.leevinapp.monitor.project.domain.ProjectRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module(includes = [ProjectViewModelModule::class])
class ProjectModule {

    @RealApi
    @FeatureScope
    @Provides
    fun providerApiService(
        retrofit: Retrofit,
        okHttpClient: OkHttpClient,
        userManager: UserManager
    ): ProjectService {
        val newRetrofit = NetworkUtil.postLogonRetrofit(retrofit, okHttpClient, userManager)
        return newRetrofit.create(ProjectService::class.java)
    }

    @Provides
    @FeatureScope
    fun providerRepository(@MockApi projectService: ProjectService): ProjectRepository {
        return ProjectRepositoryImpl(projectService)
    }

    @MockApi
    @FeatureScope
    @Provides
    fun authMockApiService(mockApiUtil: MockApiUtil): ProjectService {
        return ProjectMockServiceImpl(mockApiUtil)
    }
}
