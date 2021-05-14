package com.jeff.pizza.core.domain.di

import com.jeff.pizza.core.domain.repository.products.ProductsApi
import com.jeff.pizza.core.domain.repository.products.ProductsDataSource
import com.jeff.pizza.core.domain.repository.user.UserDataSource
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.products.ProductsResourceImpl
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.core.domain.resource.user.UserResourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesUserResource(
            dataSourceRepository: UserDataSource
    ): UserResource = UserResourceImpl(dataSourceRepository)


    @Provides
    fun providesProductsResource(
            dataSourceRepository: ProductsDataSource,
            apiRepository: ProductsApi
    ): ProductsResource = ProductsResourceImpl(dataSourceRepository, apiRepository)
}