package com.jeff.pizza.login.domain.usecase

import com.jeff.pizza.core.domain.resource.user.UserResource
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUI
import com.jeff.pizza.login.presentation.usertype.model.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SetUserTypeUseCase {
    suspend fun execute(userType: UserTypeUI)
}

class SetUserTypeUseCaseImpl
@Inject constructor(private val userResource: UserResource,
                    private val dispatcher: CoroutineDispatcher): SetUserTypeUseCase {

    override suspend fun execute(userType: UserTypeUI) {
        return withContext(dispatcher) {
            userResource.setUserType(userType.toDomain())
        }
    }
}