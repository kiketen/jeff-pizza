package com.jeff.pizza.di

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
}