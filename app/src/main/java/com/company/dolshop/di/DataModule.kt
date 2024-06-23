package com.company.dolshop.di

import android.content.Context
import androidx.room.Room
import com.company.data.datasource.local.advertisement.AnnouncemenDao
import com.company.data.datasource.local.advertisement.AnnouncementInfoDatabase
import com.company.data.datasource.local.baseproductinfo1.BaseProductDao
import com.company.data.datasource.local.baseproductinfo1.BaseProductInfoDataBase
import com.company.data.datasource.local.diarynumber.DiaryNumberDao
import com.company.data.datasource.local.diarynumber.DiaryNumberDatabase
import com.company.data.datasource.local.publicdiary.PublicDiaryDao
import com.company.data.datasource.local.publicdiary.PublicDiaryInfoDatabase
import com.company.data.datasource.local.userinfo.UserInfoDao
import com.company.data.datasource.local.userinfo.UserInfoDatabase
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
import com.company.data.datasource.local.address.AddressDao
import com.company.data.datasource.local.address.AddressDataBase
import com.company.data.datasource.local.productsale.ProductSaleDao
import com.company.data.datasource.local.productsale.ProductSaleDataBase
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

//    @Provides
//    fun provideKakaoLoginRepository(impl: KakaoLoginRepositoryImpl) : KakaoLoginRepository = impl
//    @Provides
//    fun provideKakaoLogoutRepository(impl: KakaoLogoutRepositoryImpl) : KakaoLogoutRepository = impl
//    @Provides
//    fun providegetUserKakaoInfoRepository(impl: GetUserKakaoInfoRepositoryImpl) : getUserKakaoInfoRepository = impl
//    @Provides
//    fun provideUpdateKakaoUserInfoRepository(impl: GetUserInfoDbRepositoryImpl) : GetUserInfoDbRepository = impl
//    @Provides
//    fun provideGetProductRepository(impl: getProductRepositoryImpl) : getProductRepository = impl
//    @Provides
//    fun provideUpdateBaseProductRepository(impl: UpdateBaseProductRepositoryImpl) : UpdateBaseProductRepository = impl
//    @Provides
//    fun provideCoroutineWorkerRepository(impl: CoroutineWorkerRepositoryImpl) : CoroutineWorkerRepository = impl
//    @Provides
//    fun provideAnnouncementRepository(impl: AnnouncementRepositoryImpl) : AnnouncementRepository = impl
//    @Provides
//    fun provideProductSaleRepository(impl: UpdateProductSaleRepositoryImpl) : UpdateProductSaleRepository = impl
//    @Provides
//    fun provideSaveAddressRepository(impl: SaveAddressRepositoryImpl) : SaveAddressRepository = impl
//    @Provides
//    fun provideGetAddressRepository(impl: GetAddressRepositoryImpl) : GetAddressRepository = impl
//    @Provides
//    fun provideDiaryNumberRepository(impl: DiaryNumberRoomRepositoryImpl) : DiaryNumberRoomRepository = impl
//    @Provides
//    fun provideGetDiaryWorkerFunctionRepository(impl: GetDiaryWorkerFunction) : getDiaryWorkerFunctionRepository = impl
//    @Provides
//    fun provideGetPublicDiaryWorkerFunction(impl: GetPublicDiaryWorkerFunction) : GetPublicDiaryWorkerFunctionRepository = impl
//    @Provides
//    fun provideSavePublicDiaryRepository(impl: SavePublicDiaryRepositoryImpl) : SavePublicDiaryRepository = impl
//    @Provides
//    fun provideGetPublicDiaryRepository(impl: GetPublicDiaryRepositoryImpl) : GetPublicDiaryRepository = impl
//    @Provides
//    fun provideDeletePublicDiaryRepository(impl: DeletePublicDiaryRepositoryImpl) : DeletePublicDiaryRepository = impl
//    @Provides
//    fun provideSaverFirebaseAuthRepository(impl: SaverFirebaseAuthImpl) : SaverFirebaseAuthRepository = impl


    // 파베 리얼 DB
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
    @Provides
    fun provideDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.reference  // 기본 DatabaseReference
    }

    // UserInfo DB
    @Provides
    @Singleton
    fun provideUserInfoDatabase(@ApplicationContext appContext: Context): UserInfoDatabase {
        return Room.databaseBuilder(
            appContext,
            UserInfoDatabase::class.java,
            "user_info_database"
        ).build()
    }
    @Provides
    fun provideUserInfoDao(database: UserInfoDatabase) : UserInfoDao {
        return database.dao
    }

//    @Provides
//    @Singleton
//    fun provideGetUserKakaoInfoRepositoryImpl(
//        dao : UserInfoDao
//    ) : GetUserKakaoInfoRepositoryImpl = GetUserKakaoInfoRepositoryImpl(dao)


    // ProductSale DB
    @Provides
    @Singleton
    fun provideProductSaleDataBase(@ApplicationContext appContext: Context): ProductSaleDataBase {
        return Room.databaseBuilder(
            appContext,
            ProductSaleDataBase::class.java,
            "product_sale_database"
        ).build()
    }
    @Provides
    fun provideProductSaleDao(database: ProductSaleDataBase) : ProductSaleDao {
        return database.dao
    }

    // BaseProduct DB
    @Provides
    @Singleton
    fun provideBaseProductInfoDataBase(@ApplicationContext appContext: Context): BaseProductInfoDataBase {
        return Room.databaseBuilder(
            appContext,
            BaseProductInfoDataBase::class.java,
            "base_product_database"
        ).build()
    }
    @Provides
    fun provideBaseProductDao(database: BaseProductInfoDataBase) : BaseProductDao {
        return database.dao
    }

    // AddressDB
    @Provides
    @Singleton
    fun provideAddressDataBase(@ApplicationContext appContext: Context): AddressDataBase {
        return Room.databaseBuilder(
            appContext,
            AddressDataBase::class.java,
            "address_database"
        ).build()
    }

    @Provides
    fun provideAddressDao(database: AddressDataBase) : AddressDao {
        return database.dao
    }


    // DiaryNumber DB
    @Provides
    @Singleton
    fun provideDiaryNumberDatabase(@ApplicationContext appContext: Context): DiaryNumberDatabase {
        return Room.databaseBuilder(
            appContext,
            DiaryNumberDatabase::class.java,
            "diaryNumber_database"
        ).build()
    }

    @Provides
    fun provideDiaryNumberDao(database: DiaryNumberDatabase) : DiaryNumberDao {
        return database.dao
    }

    // SavedPublicDiary DB
    @Provides
    @Singleton
    fun providePublicDiaryInfoDatabase(@ApplicationContext appContext: Context): PublicDiaryInfoDatabase {
        return Room.databaseBuilder(
            appContext,
            PublicDiaryInfoDatabase::class.java,
            "publicdiary_database"
        ).build()
    }

    @Provides
    fun providePublicDiaryDao(database: PublicDiaryInfoDatabase) : PublicDiaryDao {
        return database.dao
    }

    // SavePublicDiary
    @Provides
    @Singleton
    fun provideAdvertisementInfoDatabase(@ApplicationContext appContext: Context): AnnouncementInfoDatabase {
        return Room.databaseBuilder(
            appContext,
            AnnouncementInfoDatabase::class.java,
            "advertisement_database"
        ).build()
    }

    @Provides
    fun provideAdvertisementDao(database: AnnouncementInfoDatabase) : AnnouncemenDao {
        return database.dao
    }
}