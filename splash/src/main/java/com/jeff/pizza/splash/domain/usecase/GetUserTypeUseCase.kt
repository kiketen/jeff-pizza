package com.jeff.pizza.splash.domain.usecase

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetUserTypeUseCase {
    suspend fun execute(): UserType
}

class GetUserTypeUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val dispatcher: CoroutineDispatcher): GetUserTypeUseCase {

    override suspend fun execute(): UserType {
        return withContext(dispatcher) {
            userResource.getUserType()
        }
    }
}