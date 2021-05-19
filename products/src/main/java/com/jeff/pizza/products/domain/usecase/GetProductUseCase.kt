package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetProductUseCase {
    suspend fun execute(productId: Long): Product
}

class GetProductUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): GetProductUseCase {

    override suspend fun execute(productId: Long): Product {
        return withContext(dispatcher) {
            productsResource.getProduct(productId, userResource.getUserType())
        }
    }
}