package com.jeff.pizza.cart.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.cart.domain.usecase.GetShoppingCartInfoUseCase
import com.jeff.pizza.cart.presentation.model.ShoppingCartUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
        getShoppingCartInfoUseCase: GetShoppingCartInfoUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<ShoppingCartUIState>()
    val uiState: LiveData<ShoppingCartUIState> = _uiState

    init {
        viewModelScope.launch {
            getShoppingCartInfoUseCase.execute().either(
                    onSuccess = {
                        _uiState.postValue(ShoppingCartUIState.ShowingContent(it))
                    }
            )
        }
    }

}