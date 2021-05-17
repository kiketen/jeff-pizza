package com.jeff.pizza.login.presentation.usertype.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.usecase.GetProductsUseCase
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCase
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUI
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUIState
import com.jeff.pizza.login.presentation.usertype.model.toDomain
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTypeViewModel @Inject constructor(
        private val setUserTypeUseCase: SetUserTypeUseCase,
        private val getProductsUseCase: GetProductsUseCase,
        private val navigator: Navigator
): ViewModel() {

    private val _uiState = MutableLiveData<UserTypeUIState>()
    val uiState: LiveData<UserTypeUIState> = _uiState

    fun onSingleOptionClick() {
        _uiState.value = UserTypeUIState.Selected
    }

    fun onMarriedOptionClick() {
        _uiState.value = UserTypeUIState.Selected
    }

    fun onConfirmButtonClick(userTypeUI: UserTypeUI) {
        _uiState.postValue(UserTypeUIState.Loading)
        viewModelScope.launch {
            setUserTypeUseCase.execute(userTypeUI.toDomain())
            getProductsUseCase.execute(refresh = true).either(
                    onSuccess = {
                        navigator.navigateToFlow(NavigationFlow.Products)
                    },
                    onError = {
                        _uiState.postValue(UserTypeUIState.Error)
                    }
            )
        }
    }

}