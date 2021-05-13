package com.jeff.pizza.core.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.jeff.pizza.core.domain.model.UserType
import com.jeff.pizza.core.domain.repository.UserDataSource
import com.jeff.pizza.core.presentation.model.Either
import com.jeff.pizza.core.presentation.model.Failure
import javax.inject.Inject


class UserDataSourceRepository
@Inject constructor(
    private val context: Context
) : UserDataSource {

    private companion object {
        private const val PREFERENCE_FILE_KEY = "preferenceFileKey"
        private const val USER_TYPE = "userType"
    }

    override fun setUserType(userType: UserType) {
        val sharedPref = sharedPreferences()
        with(sharedPref.edit()) {
            putString(USER_TYPE, userType.name)
            commit()
        }
    }

    override fun getUserType(): Either<Failure.NoData, UserType> {
        val userType = sharedPreferences().getString(USER_TYPE, null)
        return when (userType) {
            UserType.SINGLE.name -> Either.Right(UserType.SINGLE)
            UserType.MARRIED.name -> Either.Right(UserType.SINGLE)
            else -> Either.Left(Failure.NoData)
        }
    }

    private fun sharedPreferences(): SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

}