package com.jeff.pizza.cart.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface OrderShoppingCartUseCase {
    suspend fun execute()
}

class OrderShoppingCartUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): OrderShoppingCartUseCase {

    override suspend fun execute() {
        return withContext(dispatcher) {
            productsResource.resetProductsCount()
        }
    }

}