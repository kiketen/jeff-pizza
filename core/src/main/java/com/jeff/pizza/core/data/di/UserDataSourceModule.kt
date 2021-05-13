package com.jeff.pizza.core.data.di

import android.content.Context
import com.jeff.pizza.core.data.datasource.UserDataSourceRepository
import com.jeff.pizza.core.domain.repository.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserDataSourceModule {

    @Provides
    fun providesUserDataSource(
        @ApplicationContext context: Context
    ): UserDataSource = UserDataSourceRepository(context)
}