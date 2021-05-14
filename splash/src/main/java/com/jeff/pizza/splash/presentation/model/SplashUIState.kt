package com.jeff.pizza.splash.presentation.model


sealed class SplashUIState {
    object Loading: SplashUIState()
    object Error: SplashUIState()
}
