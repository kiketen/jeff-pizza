package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddProductUseCase {
    suspend fun execute(productId: Long, size: String)
}

class AddProductUseCaseImpl
@Inject constructor(private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): AddProductUseCase {

    override suspend fun execute(productId: Long, size: String) {
        return withContext(dispatcher) {
            productsResource.addProduct(productId, size)
        }
    }
}