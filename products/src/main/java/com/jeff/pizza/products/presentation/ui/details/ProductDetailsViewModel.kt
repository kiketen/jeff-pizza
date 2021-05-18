package com.jeff.pizza.products.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    val uiState: LiveData<ProductDetailsUIState> = _uiState

    fun getProductDetails(productId: Long) {
        viewModelScope.launch {

        }
    }

    fun onBackButtonClick() {

    }

    fun onOrderClick(productSize: String) {

    }
}