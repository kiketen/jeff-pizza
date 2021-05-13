package com.jeff.pizza.login.data.datasource

import android.content.Context
import android.content.SharedPreferences
import com.jeff.pizza.login.domain.model.UserType
import com.jeff.pizza.login.domain.repository.UserDataSource
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

    private fun sharedPreferences(): SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

}