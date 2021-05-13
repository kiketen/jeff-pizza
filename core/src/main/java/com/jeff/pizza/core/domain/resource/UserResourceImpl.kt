package com.jeff.pizza.core.domain.resource

import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.domain.repository.UserDataSource
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure
import javax.inject.Inject


class UserResourceImpl
@Inject constructor(
        private val dataSourceRepository: UserDataSource
): UserResource {
    override fun setUserType(userType: UserType) {
        dataSourceRepository.setUserType(userType)
    }

    override fun getUserType(): Either<Failure.NoData, UserType> {
        return dataSourceRepository.getUserType()
    }
}