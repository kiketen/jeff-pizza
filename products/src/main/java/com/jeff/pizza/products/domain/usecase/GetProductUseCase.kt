package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.jeff.pizza.products.presentation.model.toDetailsUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetProductUseCase {
    suspend fun execute(productId: Long): ProductDetailsUI
}

class GetProductUseCaseImpl
@Inject constructor(private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): GetProductUseCase {

    override suspend fun execute(productId: Long): ProductDetailsUI {
        return withContext(dispatcher) {
            productsResource.getProduct(productId).toDetailsUI()
        }
    }
}