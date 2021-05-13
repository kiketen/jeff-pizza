package com.jeff.pizza.login.presentation.usertype.model

import com.jeff.pizza.login.domain.model.UserType


enum class UserTypeUI {
    SINGLE, MARRIED, UNKNOWN
}

fun UserTypeUI.toDomain() =
    when (this) {
        UserTypeUI.SINGLE -> UserType.SINGLE
        UserTypeUI.MARRIED -> UserType.MARRIED
        UserTypeUI.UNKNOWN -> UserType.UNKNOWN
    }