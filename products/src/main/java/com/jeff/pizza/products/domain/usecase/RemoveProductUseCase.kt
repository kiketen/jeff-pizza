package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveProductUseCase {
    suspend fun execute(productId: Long, size: String): Product
}

class RemoveProductUseCaseImpl
@Inject constructor(private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): RemoveProductUseCase {

    override suspend fun execute(productId: Long, size: String): Product {
        return withContext(dispatcher) {
            productsResource.removeProduct(productId, size)
        }
    }
}