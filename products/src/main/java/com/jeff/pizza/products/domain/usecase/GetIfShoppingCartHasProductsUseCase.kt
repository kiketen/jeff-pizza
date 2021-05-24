package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetIfShoppingCartHasProductsUseCase {
    suspend fun execute(): Boolean
}

class GetIfShoppingCartHasProductsUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetIfShoppingCartHasProductsUseCase {

    override suspend fun execute(): Boolean {
        return withContext(dispatcher) {
            productsResource.getShoppingCartInfo().products.isEmpty().not()
        }
    }
}