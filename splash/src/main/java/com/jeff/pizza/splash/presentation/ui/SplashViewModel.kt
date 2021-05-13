package com.jeff.pizza.splash.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
        private val getUserTypeUseCase: GetUserTypeUseCase,
        private val navigator: Navigator
): ViewModel() {

    private val _a = SingleLiveEvent<Boolean>()
    val a: LiveData<Boolean> = _a

    init {
        viewModelScope.launch {
            getUserTypeUseCase.execute().either(
                    onSuccess = {
                        //TODO call to endpoint to get pizzas
                    },
                    onError = {
                        navigator.navigateToFlow(NavigationFlow.Login)
                    }
            )
        }
    }

}