package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.presentation.extensions.isNotNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetIfShoppingCartIsEmptyUseCase {
    suspend fun execute(): Boolean
}

class GetIfShoppingCartIsEmptyUseCaseImpl @Inject constructor(
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetIfShoppingCartIsEmptyUseCase {

    override suspend fun execute(): Boolean {
        return withContext(dispatcher) {
            val productsEither = productsResource.getProducts(false)
            if (productsEither.isRight) {
                val products = productsEither.rightValue
                val product = products.firstOrNull { product -> product.prices.maxOf { it.count } > 0 }
                product.isNotNull()
            } else {
                false
            }
        }
    }
}