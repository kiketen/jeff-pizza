package com.jeff.pizza.core.presentation.di

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.core.domain.usecase.GetProductsUseCase
import com.jeff.pizza.core.domain.usecase.GetProductsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ProductsViewModelModule {

    @Provides
    fun providesSetUserTypeUseCase(
            productsResource: ProductsResource,
            userResource: UserResource,
            dispatcher: CoroutineDispatcher
    ): GetProductsUseCase = GetProductsUseCaseImpl(userResource, productsResource, dispatcher)
}