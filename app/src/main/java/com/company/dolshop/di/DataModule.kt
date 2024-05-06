package com.company.dolshop.di

import android.content.Context
import androidx.room.Room
import com.company.data.datasource.baseproductinfo1.BaseProductDao
import com.company.data.datasource.baseproductinfo1.BaseProductInfoDataBase
import com.company.data.datasource.diarynumber.DiaryNumberDao
import com.company.data.datasource.diarynumber.DiaryNumberDatabase
import com.company.data.datasource.userinfo.UserInfoDao
import com.company.data.datasource.userinfo.UserInfoDatabase
import com.company.data.repositoryimpl.AnnouncementRepositoryImpl
import com.company.data.repositoryimpl.CoroutineWorkerRepositoryImpl
import com.company.data.repositoryimpl.DiaryNumberRoomRepositoryImpl
import com.company.data.repositoryimpl.KakaoLoginRepositoryImpl
import com.company.data.repositoryimpl.KakaoLogoutRepositoryImpl
import com.company.data.repositoryimpl.UpdateBaseProductRepositoryImpl
import com.company.data.repositoryimpl.UpdateKakaoUserInfoRepositoryImpl
import com.company.data.repositoryimpl.address.GetAddressRepositoryImpl
import com.company.data.repositoryimpl.address.SaveAddressRepositoryImpl
import com.company.data.repositoryimpl.getProductRepositoryImpl
import com.company.data.repositoryimpl.getUserKakaoInfoRepositoryImpl
import com.company.datasource_local_address.AddressDao
import com.company.datasource_local_address.AddressDataBase
import com.company.datasource_local_productsale.productsale.ProductSaleDao
import com.company.datasource_local_productsale.productsale.ProductSaleDataBase
import com.company.domain.repository.AnnouncementRepository
import com.company.domain.repository.CoroutineWorkerRepository
import com.company.domain.repository.DiaryNumberRoomRepository
import com.company.domain.repository.GetAddressRepository
import com.company.domain.repository.KakaoLoginRepository
import com.company.domain.repository.KakaoLogoutRepository
import com.company.domain.repository.SaveAddressRepository
import com.company.domain.repository.UpdateBaseProductRepository
import com.company.domain.repository.UpdateKakaoUserInfoRepository
import com.company.domain.repository.UpdateProductSaleRepository
import com.company.domain.repository.getProductRepository
import com.company.domain.repository.getUserKakaoInfoRepository
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

    // RepositoryImpl 객체를 Repository 타입으로 주입한다. 즉, Repository를 사용하는데서는 RepositoryImpl도 사용 할 수 있게 Hilt가 인스턴스를 제공해주는거야.
    @Provides
    fun provideKakaoLoginRepository(impl: KakaoLoginRepositoryImpl) : KakaoLoginRepository = impl

    @Provides
    fun provideKakaoLogoutRepository(impl: KakaoLogoutRepositoryImpl) : KakaoLogoutRepository = impl
    @Provides
    fun providegetUserKakaoInfoRepository(impl: getUserKakaoInfoRepositoryImpl) : getUserKakaoInfoRepository = impl
    @Provides
    fun provideUpdateKakaoUserInfoRepository(impl: UpdateKakaoUserInfoRepositoryImpl) : UpdateKakaoUserInfoRepository = impl
    @Provides
    fun provideGetProductRepository(impl: getProductRepositoryImpl) : getProductRepository = impl
    @Provides
    fun provideUpdateBaseProductRepository(impl: UpdateBaseProductRepositoryImpl) : UpdateBaseProductRepository = impl
    @Provides
    fun provideCoroutineWorkerRepository(impl: CoroutineWorkerRepositoryImpl) : CoroutineWorkerRepository = impl
    @Provides
    fun provideAnnouncementRepository(impl: AnnouncementRepositoryImpl) : AnnouncementRepository = impl
    @Provides
    fun provideProductSaleRepository(impl: com.company.update_productsale_impl.UpdateProductSaleRepositoryImpl) : UpdateProductSaleRepository = impl
    @Provides
    fun provideSaveAddressRepository(impl: SaveAddressRepositoryImpl) : SaveAddressRepository = impl
    @Provides
    fun provideGetAddressRepository(impl: GetAddressRepositoryImpl) : GetAddressRepository = impl
    @Provides
    fun provideDiaryNumberRepository(impl: DiaryNumberRoomRepositoryImpl) : DiaryNumberRoomRepository = impl


    // 추후 수정
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.reference  // 기본 DatabaseReference
    }
    // 추후 수정

    // datasource module

    // userinfo db
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

    @Provides
    @Singleton
    fun providegetUserKakaoInfoRepositoryImpl(
        dao : UserInfoDao
    ) : getUserKakaoInfoRepositoryImpl = getUserKakaoInfoRepositoryImpl(dao)


    // product sale db
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

    // baseproduct db
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

    // address
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
    fun provideAddresstDao(database: AddressDataBase) : AddressDao {
        return database.dao
    }

    // DiaryNumber

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
}