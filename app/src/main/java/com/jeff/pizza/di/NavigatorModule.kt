package com.jeff.pizza.di

import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigatorModule {
    @Singleton
    @Provides
    fun providesNavigator(): Navigator = NavigatorImpl()
}