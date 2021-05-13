package com.jeff.pizza.login.data.di

import android.content.Context
import com.jeff.pizza.login.data.datasource.UserDataSourceRepository
import com.jeff.pizza.login.domain.repository.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginDataSourceModule {

    @Provides
    fun providesUserDataSource(
        @ApplicationContext context: Context
    ): UserDataSource = UserDataSourceRepository(context)
}