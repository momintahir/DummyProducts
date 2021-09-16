package com.example.dummyproducts.di

import android.app.Application
import androidx.room.Room
import com.example.dummyproducts.api.ProductsApi
import com.example.dummyproducts.db.ProductsDatabase
import com.example.dummyproducts.repositories.ProductsRepository
import com.example.dummyproducts.util.Constants.Companion.BASE_URL


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitAPI(): ProductsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)


    @Singleton
    @Provides
    fun provideDatabase(app: Application): ProductsDatabase =
        Room.databaseBuilder(app, ProductsDatabase::class.java, "products_db")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun providesRepository(db: ProductsDatabase, api: ProductsApi):
            ProductsRepository = ProductsRepository(db, api)

}