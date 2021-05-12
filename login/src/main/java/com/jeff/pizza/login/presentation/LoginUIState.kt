package com.jeff.pizza.login.presentation


sealed class LoginUIState {
    object Unselected : LoginUIState()
    object Selected : LoginUIState()
    object Loading : LoginUIState()
}
