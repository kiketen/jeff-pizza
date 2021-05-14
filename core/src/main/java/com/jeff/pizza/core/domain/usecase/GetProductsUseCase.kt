package com.jeff.pizza.core.domain.usecase

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.Product
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetProductsUseCase {
    suspend fun execute(refresh: Boolean): Either<Failure, List<Product>>
}

class GetProductsUseCaseImpl @Inject constructor(
        private val userResource: UserResource,
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetProductsUseCase {

    override suspend fun execute(refresh: Boolean): Either<Failure, List<Product>> {
        return withContext(dispatcher) {
            productsResource.getProducts(refresh)
        }
    }
}