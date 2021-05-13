package com.jeff.pizza.splash.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCase
import com.jeff.pizza.splash.presentation.model.SplashNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
        private val getUserTypeUseCase: GetUserTypeUseCase
): ViewModel() {

    private val _navigator = SingleLiveEvent<SplashNavigation>()
    val navigator: LiveData<SplashNavigation> = _navigator

    init {
        viewModelScope.launch {
            getUserTypeUseCase.execute().either(
                    onSuccess = {
                        //TODO call to endpoint to get pizzas
                    },
                    onError = {
                        _navigator.postValue(SplashNavigation.Login)
                    }
            )
        }
    }

}