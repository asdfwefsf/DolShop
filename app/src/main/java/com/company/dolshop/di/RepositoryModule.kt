package com.company.dolshop.di

import com.company.data.datasource.local.userinfo.UserInfoDao
import com.company.data.repositoryimpl.AnnouncementRepositoryImpl
import com.company.data.repositoryimpl.CoroutineWorkerRepositoryImpl
import com.company.data.repositoryimpl.DiaryNumberRoomRepositoryImpl
import com.company.data.repositoryimpl.GetUserInfoDbRepositoryImpl
import com.company.data.repositoryimpl.KakaoLoginRepositoryImpl
import com.company.data.repositoryimpl.KakaoLogoutRepositoryImpl
import com.company.data.repositoryimpl.UpdateBaseProductRepositoryImpl
import com.company.data.repositoryimpl.UpdateProductSaleRepositoryImpl
import com.company.data.repositoryimpl.address.GetAddressRepositoryImpl
import com.company.data.repositoryimpl.address.SaveAddressRepositoryImpl
import com.company.data.repositoryimpl.firebase.SaverFirebaseAuthImpl
import com.company.data.repositoryimpl.getProductRepositoryImpl
import com.company.data.repositoryimpl.GetUserKakaoInfoRepositoryImpl
import com.company.data.repositoryimpl.publicdiary.DeletePublicDiaryRepositoryImpl
import com.company.data.repositoryimpl.publicdiary.GetPublicDiaryRepositoryImpl
import com.company.data.repositoryimpl.publicdiary.SavePublicDiaryRepositoryImpl
import com.company.data.worker.GetDiaryWorkerFunction
import com.company.data.worker.GetPublicDiaryWorkerFunction
import com.company.domain.repository.AnnouncementRepository
import com.company.domain.repository.CoroutineWorkerRepository
import com.company.domain.repository.DiaryNumberRoomRepository
import com.company.domain.repository.GetAddressRepository
import com.company.domain.repository.GetPublicDiaryWorkerFunctionRepository
import com.company.domain.repository.GetUserInfoDbRepository
import com.company.domain.repository.KakaoLoginRepository
import com.company.domain.repository.KakaoLogoutRepository
import com.company.domain.repository.SaveAddressRepository
import com.company.domain.repository.UpdateBaseProductRepository
import com.company.domain.repository.UpdateProductSaleRepository
import com.company.domain.repository.firebase.SaverFirebaseAuthRepository
import com.company.domain.repository.GetDiaryWorkerFunctionRepository
import com.company.domain.repository.getProductRepository
import com.company.domain.repository.getUserKakaoInfoRepository
import com.company.domain.repository.publicidary.DeletePublicDiaryRepository
import com.company.domain.repository.publicidary.GetPublicDiaryRepository
import com.company.domain.repository.publicidary.SavePublicDiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideKakaoLoginRepository(impl: KakaoLoginRepositoryImpl) : KakaoLoginRepository = impl
    @Provides
    fun provideKakaoLogoutRepository(impl: KakaoLogoutRepositoryImpl) : KakaoLogoutRepository = impl
    @Provides
    fun providegetUserKakaoInfoRepository(impl: GetUserKakaoInfoRepositoryImpl) : getUserKakaoInfoRepository = impl
    @Provides
    fun provideUpdateKakaoUserInfoRepository(impl: GetUserInfoDbRepositoryImpl) : GetUserInfoDbRepository = impl
    @Provides
    fun provideGetProductRepository(impl: getProductRepositoryImpl) : getProductRepository = impl
    @Provides
    fun provideUpdateBaseProductRepository(impl: UpdateBaseProductRepositoryImpl) : UpdateBaseProductRepository = impl
    @Provides
    fun provideCoroutineWorkerRepository(impl: CoroutineWorkerRepositoryImpl) : CoroutineWorkerRepository = impl
    @Provides
    fun provideAnnouncementRepository(impl: AnnouncementRepositoryImpl) : AnnouncementRepository = impl
    @Provides
    fun provideProductSaleRepository(impl: UpdateProductSaleRepositoryImpl) : UpdateProductSaleRepository = impl
    @Provides
    fun provideSaveAddressRepository(impl: SaveAddressRepositoryImpl) : SaveAddressRepository = impl
    @Provides
    fun provideGetAddressRepository(impl: GetAddressRepositoryImpl) : GetAddressRepository = impl
    @Provides
    fun provideDiaryNumberRepository(impl: DiaryNumberRoomRepositoryImpl) : DiaryNumberRoomRepository = impl
    @Provides
    fun provideGetDiaryWorkerFunctionRepository(impl: GetDiaryWorkerFunction) : GetDiaryWorkerFunctionRepository = impl
    @Provides
    fun provideGetPublicDiaryWorkerFunction(impl: GetPublicDiaryWorkerFunction) : GetPublicDiaryWorkerFunctionRepository = impl
    @Provides
    fun provideSavePublicDiaryRepository(impl: SavePublicDiaryRepositoryImpl) : SavePublicDiaryRepository = impl
    @Provides
    fun provideGetPublicDiaryRepository(impl: GetPublicDiaryRepositoryImpl) : GetPublicDiaryRepository = impl
    @Provides
    fun provideDeletePublicDiaryRepository(impl: DeletePublicDiaryRepositoryImpl) : DeletePublicDiaryRepository = impl
    @Provides
    fun provideSaverFirebaseAuthRepository(impl: SaverFirebaseAuthImpl) : SaverFirebaseAuthRepository = impl
//



    @Provides
    @Singleton
    fun provideGetUserKakaoInfoRepositoryImpl(
        dao : UserInfoDao
    ) : GetUserKakaoInfoRepositoryImpl = GetUserKakaoInfoRepositoryImpl(dao)

}