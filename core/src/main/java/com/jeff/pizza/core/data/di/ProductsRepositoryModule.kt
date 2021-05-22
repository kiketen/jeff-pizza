package com.jeff.pizza.core.data.di

import com.jeff.pizza.core.data.repository.AppDatabase
import com.jeff.pizza.core.data.repository.products.*
import com.jeff.pizza.core.domain.repository.products.ProductsApi
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ProductsRepositoryModule {

    @Provides
    fun providesProductsApi(
            apiDefinition: ProductsApiDefinition
    ): ProductsApi = ProductsApiImpl(apiDefinition)

    @Provides
    fun providesProductsApiDefinition(retrofit: Retrofit): ProductsApiDefinition = retrofit.create(ProductsApiDefinition::class.java)

    @Provides
    fun providesProductsDataSource(
            productsDAO: ProductsDAO,
            pricesDAO: PricesDAO
    ): ProductsDataSource = ProductsDataSourceImpl(productsDAO, pricesDAO)

    @Provides
    fun provideProductsDAO(appDatabase: AppDatabase): ProductsDAO {
        return appDatabase.productsDAO()
    }

    @Provides
    fun providePricesDAO(appDatabase: AppDatabase): PricesDAO {
        return appDatabase.pricesDAO()
    }
}