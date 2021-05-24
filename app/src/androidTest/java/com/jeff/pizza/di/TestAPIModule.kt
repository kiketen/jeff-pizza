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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestAPIModule {

    companion object {
        private const val BASE_URL = "http://localhost:8080/"
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

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