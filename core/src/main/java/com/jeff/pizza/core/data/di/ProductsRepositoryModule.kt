package com.jeff.pizza.core.data.di

import com.jeff.pizza.core.data.repository.AppDatabase
import com.jeff.pizza.core.data.repository.products.PricesDAO
import com.jeff.pizza.core.data.repository.products.ProductsApiDefinition
import com.jeff.pizza.core.data.repository.products.ProductsApiImpl
import com.jeff.pizza.core.data.repository.products.ProductsDAO
import com.jeff.pizza.core.data.repository.products.ProductsDataSourceImpl
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
    fun provideProductsDAO(appDatabase: AppDatabase): ProductsDAO {
        return appDatabase.productsDAO()
    }

    @Provides
    fun providePricesDAO(appDatabase: AppDatabase): PricesDAO {
        return appDatabase.pricesDAO()
    }
}