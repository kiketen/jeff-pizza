package com.jeff.pizza.core.domain.repository.user

import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure


interface UserDataSource {
    fun setUserType(userType: UserType)
    fun getUserType(): Either<Failure.NoData, UserType>
}