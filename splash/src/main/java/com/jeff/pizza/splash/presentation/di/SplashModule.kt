package com.jeff.pizza.splash.presentation.di

import com.jeff.pizza.core.domain.resource.UserResource
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCase
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object SplashModule {

    @Provides
    fun providesSetUserTypeUseCase(
            resource: UserResource,
            dispatcher: CoroutineDispatcher
    ): GetUserTypeUseCase = GetUserTypeUseCaseImpl(resource, dispatcher)
}