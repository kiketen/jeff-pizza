package com.jeff.pizza.core.domain.resource.user

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.user.UserDataSource
import javax.inject.Inject


class UserResourceImpl
@Inject constructor(
        private val dataSourceRepository: UserDataSource
): UserResource {
    override fun setUserType(userType: UserType) {
        dataSourceRepository.setUserType(userType)
    }

    override fun getUserType(): UserType {
        return dataSourceRepository.getUserType()
    }
}