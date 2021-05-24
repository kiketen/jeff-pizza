package com.jeff.pizza.core.domain.usecase

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.core.presentation.model.ProductUI
import com.jeff.pizza.core.presentation.model.toUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetProductsUseCase {
    suspend fun execute(refresh: Boolean): Either<Failure, List<ProductUI>>
}

class GetProductsUseCaseImpl @Inject constructor(
        private val userResource: UserResource,
        private val productsResource: ProductsResource,
        private val dispatcher: CoroutineDispatcher): GetProductsUseCase {

    override suspend fun execute(refresh: Boolean): Either<Failure, List<ProductUI>> {
        return withContext(dispatcher) {
            productsResource.getProducts(refresh, userResource.getUserType()).map { it.toUI() }
        }
    }
}