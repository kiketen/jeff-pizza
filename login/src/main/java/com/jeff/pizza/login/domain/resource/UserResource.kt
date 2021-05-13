package com.jeff.pizza.login.domain.resource

import com.jeff.pizza.login.domain.model.UserType


interface UserResource {
    fun setUserType(userType: UserType)
}