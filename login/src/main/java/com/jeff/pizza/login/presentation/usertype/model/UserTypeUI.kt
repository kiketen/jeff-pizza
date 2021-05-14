package com.jeff.pizza.login.presentation.usertype.model

import com.jeff.pizza.core.domain.model.user.UserType


enum class UserTypeUI {
    SINGLE, MARRIED, UNKNOWN
}

fun UserTypeUI.toDomain() =
    when (this) {
        UserTypeUI.SINGLE -> UserType.SINGLE
        UserTypeUI.MARRIED -> UserType.MARRIED
        UserTypeUI.UNKNOWN -> UserType.UNKNOWN
    }