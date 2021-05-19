package com.jeff.pizza.core.domain.resource.user

import com.jeff.pizza.core.domain.model.user.UserType


interface UserResource {
    fun setUserType(userType: UserType)
    fun getUserType(): UserType
}