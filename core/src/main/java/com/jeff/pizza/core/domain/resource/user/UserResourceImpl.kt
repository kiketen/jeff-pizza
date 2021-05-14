package com.jeff.pizza.core.domain.resource.user

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.user.UserDataSource
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
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