package com.company.di

import com.company.data.repositoryimpl.TestRepositoryImpl
import com.company.domain.repository.TempRepository
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

}