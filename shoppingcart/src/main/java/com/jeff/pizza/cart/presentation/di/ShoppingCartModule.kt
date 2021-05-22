package com.jeff.pizza.cart.presentation.di

import com.jeff.pizza.cart.domain.usecase.GetShoppingCartInfoUseCase
import com.jeff.pizza.cart.domain.usecase.GetShoppingCartInfoUseCaseImpl
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ShoppingCartModule {

    @Provides
    fun providesGetShoppingCartInfoUseCase(
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): GetShoppingCartInfoUseCase = GetShoppingCartInfoUseCaseImpl(productsResource, dispatcher)

}