package com.jeff.pizza.core.data.datasource

import android.content.SharedPreferences
import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.domain.repository.UserDataSource
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure
import javax.inject.Inject


class UserDataSourceRepository
@Inject constructor(
        private val sharedPreferences: SharedPreferences
): UserDataSource {

    private companion object {
        private const val USER_TYPE = "userType"
    }

    override fun setUserType(userType: UserType) {
        with(sharedPreferences.edit()) {
            putString(USER_TYPE, userType.name)
            commit()
        }
    }

    override fun getUserType(): Either<Failure.NoData, UserType> {
        val userType = sharedPreferences.getString(USER_TYPE, null)
        return when (userType) {
            UserType.SINGLE.name -> Either.Right(UserType.SINGLE)
            UserType.MARRIED.name -> Either.Right(UserType.MARRIED)
            else -> Either.Left(Failure.NoData)
        }
    }

}