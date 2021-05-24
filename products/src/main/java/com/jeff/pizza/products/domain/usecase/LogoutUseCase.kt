package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.resource.products.ProductsResource
import com.jeff.pizza.core.domain.resource.user.UserResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LogoutUseCase {
    suspend fun execute()
}

class LogoutUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val productsResource: ProductsResource,
                    private val dispatcher: CoroutineDispatcher): LogoutUseCase {

    override suspend fun execute() {
        return withContext(dispatcher) {
            userResource.setUserType(UserType.UNKNOWN)
            productsResource.resetProductsCount()
        }
    }
}