package com.jeff.pizza.login.presentation.usertype.di

import android.content.Context
import com.jeff.pizza.login.data.datasource.UserDataSourceRepository
import com.jeff.pizza.login.domain.repository.UserDataSource
import com.jeff.pizza.login.domain.resource.UserResource
import com.jeff.pizza.login.domain.resource.UserResourceImpl
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCase
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object UserTypeModule {

    @Provides
    fun providesSetUserTypeUseCase(
            resource: UserResource,
            dispatcher: CoroutineDispatcher
    ): SetUserTypeUseCase = SetUserTypeUseCaseImpl(resource, dispatcher)

    @Provides
    fun providesUserResource(
            dataSourceRepository: UserDataSource
    ): UserResource = UserResourceImpl(dataSourceRepository)
}