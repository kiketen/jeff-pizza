package com.jeff.pizza.cart.domain

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetShoppingCartInfoUseCase {
    suspend fun execute(): Boolean
}

class GetShoppingCartInfoUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetShoppingCartInfoUseCase {

    override suspend fun execute(): Boolean {
        return withContext(dispatcher) {
            productsResource.getProductsAdded().isNullOrEmpty()
        }
    }
}