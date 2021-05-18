package com.jeff.pizza.products.presentation.di

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.domain.usecase.GetProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ProductDetailsModule {

    @Provides
    fun providesGetProductDetailsUseCase(
            resource: UserResource,
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): GetProductUseCase = GetProductUseCaseImpl(resource, productsResource, dispatcher)
}