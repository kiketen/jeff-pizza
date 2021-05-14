package com.jeff.pizza.login.domain.usecase

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.resource.user.UserResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SetUserTypeUseCase {
    suspend fun execute(userType: UserType)
}

class SetUserTypeUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val dispatcher: CoroutineDispatcher): SetUserTypeUseCase {

    override suspend fun execute(userType: UserType) {
        return withContext(dispatcher) {
            userResource.setUserType(userType)
        }
    }
}