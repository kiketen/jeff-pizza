package com.jeff.pizza.login.domain.resource

import com.jeff.pizza.login.domain.repository.UserDataSource
import com.jeff.pizza.login.domain.model.UserType
import javax.inject.Inject


class UserResourceImpl
@Inject constructor(
        private val dataSourceRepository: UserDataSource
): UserResource {
    override fun setUserType(userType: UserType) {
        dataSourceRepository.setUserType(userType)
    }
}