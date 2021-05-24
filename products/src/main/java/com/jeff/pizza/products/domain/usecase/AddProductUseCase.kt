package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.jeff.pizza.products.presentation.model.toDetailsUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddProductUseCase {
    suspend fun execute(productId: Long, size: String): ProductDetailsUI
}

class AddProductUseCaseImpl
@Inject constructor(private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): AddProductUseCase {

    override suspend fun execute(productId: Long, size: String): ProductDetailsUI {
        return withContext(dispatcher) {
            productsResource.addProductPrice(productId, size).toDetailsUI()
        }
    }
}