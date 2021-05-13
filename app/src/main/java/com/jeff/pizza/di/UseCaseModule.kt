package com.jeff.pizza.di

import com.jeff.pizza.core.domain.repository.UserDataSource
import com.jeff.pizza.core.domain.resource.UserResource
import com.jeff.pizza.core.domain.resource.UserResourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesCoroutineDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesUserResource(
            dataSourceRepository: UserDataSource
    ): UserResource = UserResourceImpl(dataSourceRepository)
}