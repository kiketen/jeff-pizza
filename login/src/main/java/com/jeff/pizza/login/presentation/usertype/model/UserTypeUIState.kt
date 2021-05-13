package com.jeff.pizza.login.presentation.usertype.model


sealed class UserTypeUIState {
    object Unselected : UserTypeUIState()
    object Selected : UserTypeUIState()
    object Loading : UserTypeUIState()
}
