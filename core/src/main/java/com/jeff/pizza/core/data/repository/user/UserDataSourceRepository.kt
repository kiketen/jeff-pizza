package com.jeff.pizza.core.data.repository.user

import android.content.SharedPreferences
import com.jeff.pizza.core.domain.model.user.UserType
import com.jeff.pizza.core.domain.repository.user.UserDataSource
import com.jeff.pizza.core.domain.model.base.Either
import com.jeff.pizza.core.domain.model.base.Failure
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

    override fun getUserType(): UserType {
        val userType = sharedPreferences.getString(USER_TYPE, null)
        return when (userType) {
            UserType.SINGLE.name -> UserType.SINGLE
            UserType.MARRIED.name -> UserType.MARRIED
            else -> UserType.UNKNOWN
        }
    }

}