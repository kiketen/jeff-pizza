package com.jeff.pizza.products.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.products.domain.usecase.AddProductUseCase
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartIsEmptyUseCase
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.domain.usecase.RemoveProductUseCase
import com.jeff.pizza.products.presentation.model.toDetailsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
        private val getProductUseCase: GetProductUseCase,
        private val addProductUseCase: AddProductUseCase,
        private val removeProductUseCase: RemoveProductUseCase,
        private val getIfShoppingCartIsEmptyUseCase: GetIfShoppingCartIsEmptyUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    val uiState: LiveData<ProductDetailsUIState> = _uiState
    private val _shoppingCartWithProducts = MutableLiveData<Boolean>()
    val shoppingCartWithProducts: LiveData<Boolean> = _shoppingCartWithProducts

    fun getProductDetails(productId: Long) {
        viewModelScope.launch {
            _shoppingCartWithProducts.postValue(getIfShoppingCartIsEmptyUseCase.execute())
            val product = getProductUseCase.execute(productId)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product.toDetailsUI()))
        }
    }

    fun onBackButtonClick() {
        _uiState.postValue(ProductDetailsUIState.Back)
    }

    fun onAddClick(productId: Long, size: String) {
        _shoppingCartWithProducts.postValue(true)
        viewModelScope.launch {
            addProductUseCase.execute(productId, size)
        }
    }

    fun onRemoveClick(productId: Long, size: String) {
        viewModelScope.launch {
            removeProductUseCase.execute(productId, size)
            _shoppingCartWithProducts.postValue(getIfShoppingCartIsEmptyUseCase.execute())
        }
    }
}