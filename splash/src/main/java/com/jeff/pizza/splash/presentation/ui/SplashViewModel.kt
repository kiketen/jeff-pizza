package com.jeff.pizza.splash.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.usecase.DelayUseCase
import com.jeff.pizza.core.domain.usecase.GetProductsUseCase
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.splash.domain.usecase.GetUserTypeUseCase
import com.jeff.pizza.splash.presentation.model.SplashUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
        private val getUserTypeUseCase: GetUserTypeUseCase,
        private val getProductsUseCase: GetProductsUseCase,
        private val delayUseCase: DelayUseCase,
        private val navigator: Navigator
): ViewModel() {

    private companion object {
        private const val SPLASH_SCREEN_DELAY_IN_MILLISECONDS = 1000L
    }

    private val _uiState = MutableLiveData<SplashUIState>()
    val uiState: LiveData<SplashUIState> = _uiState

    init {
        viewModelScope.launch {
            getUserTypeUseCase.execute().either(
                    onSuccess = {
                        getProducts(SPLASH_SCREEN_DELAY_IN_MILLISECONDS)
                    },
                    onError = {
                        navigateToLoginAfterDelay()
                    }
            )
        }
    }

    fun onRetryClick() {
        getProducts(delayInMilliseconds = 0)
    }

    private fun getProducts(delayInMilliseconds: Long) {
        _uiState.value = SplashUIState.Loading
        viewModelScope.launch {
            val delayResponse = async { delayUseCase.execute(delayInMilliseconds) }
            val productsResponse = async { getProductsUseCase.execute(refresh = true) }
            delayResponse.await()
            productsResponse.await().either(
                    onSuccess = {
                        navigator.navigateToFlow(NavigationFlow.Products)
                    },
                    onError = {
                        _uiState.postValue(SplashUIState.Error)
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