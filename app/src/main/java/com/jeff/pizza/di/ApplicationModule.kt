package com.jeff.pizza.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeff.pizza.core.data.repository.products.PricesDAO
import com.jeff.pizza.core.data.repository.products.ProductsDAO
import com.jeff.pizza.core.data.repository.products.ProductsDataSourceImpl
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/eliseo-juan/c9c124b0899ae9adc254146783c0b764/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesProductsDataSource(
            productsDAO: ProductsDAO,
            pricesDAO: PricesDAO
    ): ProductsDataSource = ProductsDataSourceImpl(productsDAO, pricesDAO)
}