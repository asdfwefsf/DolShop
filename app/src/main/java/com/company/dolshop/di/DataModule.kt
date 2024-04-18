package com.company.dolshop.di

import com.company.data.repositoryimpl.KakaoLoginRepositoryImpl
import com.company.data.repositoryimpl.KakaoLogoutRepositoryImpl
import com.company.data.repositoryimpl.TestRepositoryImpl
import com.company.data.repositoryimpl.getUserKakaoInfoRepositoryImpl
import com.company.domain.repository.KakaoLoginRepository
import com.company.domain.repository.KakaoLogoutRepository
import com.company.domain.repository.TempRepository
import com.company.domain.repository.getUserKakaoInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun bindTempRepository(tempRepositoryImpl: TestRepositoryImpl) : TempRepository = tempRepositoryImpl
    // RepositoryImpl 객체를 Repository 타입으로 주입한다. 즉, Repository를 사용하는데서는 RepositoryImpl도 사용 할 수 있게 Hilt가 인스턴스를 제공해주는거야.
    @Provides
    fun provideKakaoLoginRepository(impl: KakaoLoginRepositoryImpl) : KakaoLoginRepository = impl

    @Provides
    fun provideKakaoLogoutRepository(impl: KakaoLogoutRepositoryImpl) : KakaoLogoutRepository = impl
    @Provides
    fun providegetUserKakaoInfoRepository(impl: getUserKakaoInfoRepositoryImpl) : getUserKakaoInfoRepository = impl
}