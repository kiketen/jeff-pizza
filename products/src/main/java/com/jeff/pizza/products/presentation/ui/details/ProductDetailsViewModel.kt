package com.jeff.pizza.products.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.presentation.model.toDetailsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
        private val getProductUseCase: GetProductUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    val uiState: LiveData<ProductDetailsUIState> = _uiState

    fun getProductDetails(productId: Long) {
        viewModelScope.launch {
            val product = getProductUseCase.execute(productId)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product.toDetailsUI()))
        }
    }

    fun onBackButtonClick() {
        _uiState.postValue(ProductDetailsUIState.Back)
    }

    fun onAddClick(size: String) {

    }

    fun onRemoveClick(size: String) {

    }
}