package com.jeff.pizza.splash.domain.usecase

import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.domain.resource.UserResource
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetUserTypeUseCase {
    suspend fun execute(): Either<Failure, UserType>
}

class GetUserTypeUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val dispatcher: CoroutineDispatcher): GetUserTypeUseCase {

    override suspend fun execute(): Either<Failure, UserType> {
        return withContext(dispatcher) {
            userResource.getUserType()
        }
    }
}