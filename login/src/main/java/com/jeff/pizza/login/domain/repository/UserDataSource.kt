package com.jeff.pizza.login.domain.repository

import com.jeff.pizza.login.domain.model.UserType


interface UserDataSource {
    fun setUserType(userType: UserType)
}