package com.jeff.pizza.login.presentation.usertype.di

import com.jeff.pizza.core.domain.repository.UserDataSource
import com.jeff.pizza.core.domain.resource.UserResource
import com.jeff.pizza.core.domain.resource.UserResourceImpl
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCase
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object UserTypeModule {

    @Provides
    fun providesSetUserTypeUseCase(
            resource: UserResource,
            dispatcher: CoroutineDispatcher
    ): SetUserTypeUseCase = SetUserTypeUseCaseImpl(resource, dispatcher)
}