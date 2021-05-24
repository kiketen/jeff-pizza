package com.jeff.pizza.products.presentation.di

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.products.domain.usecase.AddProductUseCase
import com.jeff.pizza.products.domain.usecase.AddProductUseCaseImpl
import com.jeff.pizza.products.domain.usecase.AddSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.AddSpecialProductUseCaseImpl
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartHasProductsUseCase
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartHasProductsUseCaseImpl
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.domain.usecase.GetProductUseCaseImpl
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCaseImpl
import com.jeff.pizza.products.domain.usecase.LogoutUseCase
import com.jeff.pizza.products.domain.usecase.LogoutUseCaseImpl
import com.jeff.pizza.products.domain.usecase.RemoveProductUseCase
import com.jeff.pizza.products.domain.usecase.RemoveProductUseCaseImpl
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
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): GetProductUseCase = GetProductUseCaseImpl(productsResource, dispatcher)

    @Provides
    fun providesAddProductUseCase(
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): AddProductUseCase = AddProductUseCaseImpl(productsResource, dispatcher)

    @Provides
    fun providesRemoveProductUseCase(
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): RemoveProductUseCase = RemoveProductUseCaseImpl(productsResource, dispatcher)

    @Provides
    fun providesGetIfShoppingCartIsEmptyUseCase(
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): GetIfShoppingCartHasProductsUseCase = GetIfShoppingCartHasProductsUseCaseImpl(productsResource, dispatcher)

    @Provides
    fun providesGetSpecialProductUseCase(
            userResource: UserResource,
            dispatcher: CoroutineDispatcher
    ): GetSpecialProductUseCase = GetSpecialProductUseCaseImpl(userResource, dispatcher)

    @Provides
    fun providesAddSpecialProductUseCase(
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): AddSpecialProductUseCase = AddSpecialProductUseCaseImpl(productsResource, dispatcher)

    @Provides
    fun providesLogoutUseCase(
            userResource: UserResource,
            productsResource: ProductsResource,
            dispatcher: CoroutineDispatcher
    ): LogoutUseCase = LogoutUseCaseImpl(userResource, productsResource, dispatcher)
}