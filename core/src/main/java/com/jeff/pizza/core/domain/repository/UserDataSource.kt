package com.jeff.pizza.core.domain.repository

import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure


interface UserDataSource {
    fun setUserType(userType: UserType)
    fun getUserType(): Either<Failure.NoData, UserType>
}