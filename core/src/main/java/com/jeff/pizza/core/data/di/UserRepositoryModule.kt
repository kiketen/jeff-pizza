package com.jeff.pizza.core.data.di

import android.content.SharedPreferences
import com.jeff.pizza.core.data.repository.user.UserDataSourceRepository
import com.jeff.pizza.core.domain.repository.user.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    fun providesUserDataSource(
            sharedPreferences: SharedPreferences
    ): UserDataSource = UserDataSourceRepository(sharedPreferences)
}