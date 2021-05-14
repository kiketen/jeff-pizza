package com.jeff.pizza.core.domain.resource.user

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure


interface UserResource {
    fun setUserType(userType: UserType)
    fun getUserType(): Either<Failure.NoData, UserType>
}