package com.jeff.pizza.products.domain.usecase

import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.resource.user.UserResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetSpecialProductUseCase {
    suspend fun execute(): Either<Failure.NoData, SpecialProduct>
}

class GetSpecialProductUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val dispatcher: CoroutineDispatcher): GetSpecialProductUseCase {

    companion object {
        val specialProduct = SpecialProduct("Titanic", 3.5F, "€", 1)
    }

    override suspend fun execute(): Either<Failure.NoData, SpecialProduct> {
        return withContext(dispatcher) {
            if (userResource.getUserType() == UserType.MARRIED) {
                Either.Right(specialProduct)
            } else {
                Either.Left(Failure.NoData)
            }
        }
    }
}