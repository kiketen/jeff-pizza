package com.jeff.pizza.splash.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.usecase.DelayUseCase
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
        private val getUserTypeUseCase: GetUserTypeUseCase,
        private val delayUseCase: DelayUseCase,
        private val navigator: Navigator
): ViewModel() {

    private companion object {
        private const val SPLASH_SCREEN_DELAY_IN_MILLISECONDS = 1000L
    }

    init {
        viewModelScope.launch {
            getUserTypeUseCase.execute().either(
                    onSuccess = {
                        //TODO call to endpoint to get pizzas
                    },
                    onError = {
                        navigateToLoginAfterDelay()
                    }
            )
        }
    }

    private fun navigateToLoginAfterDelay() {
        viewModelScope.launch {
            delayUseCase.execute(SPLASH_SCREEN_DELAY_IN_MILLISECONDS)
            navigator.navigateToFlow(NavigationFlow.Login)
        }
    }

}