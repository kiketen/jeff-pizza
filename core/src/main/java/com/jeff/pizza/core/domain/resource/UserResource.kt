package com.jeff.pizza.core.domain.resource

import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure


interface UserResource {
    fun setUserType(userType: UserType)
    fun getUserType(): Either<Failure.NoData, UserType>
}