package com.jeff.pizza.cart.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeff.pizza.cart.model.ShoppingCartUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableLiveData<ShoppingCartUIState>()
    val uiState: LiveData<ShoppingCartUIState> = _uiState

    init {

    }

}