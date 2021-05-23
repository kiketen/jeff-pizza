package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddSpecialProductUseCase {
    suspend fun execute(specialProduct: SpecialProduct)
}

class AddSpecialProductUseCaseImpl
@Inject constructor(private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): AddSpecialProductUseCase {

    override suspend fun execute(specialProduct: SpecialProduct) {
        return withContext(dispatcher) {
            productsResource.addSpecialProduct(specialProduct)
        }
    }
}