package com.jeff.pizza.products.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.products.domain.usecase.AddProductUseCase
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartHasProductsUseCase
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.RemoveProductUseCase
import com.jeff.pizza.products.presentation.model.ProductDetailsUIState
import com.jeff.pizza.products.presentation.model.toDetailsUI
import com.linhoapps.products.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
        private val getProductUseCase: GetProductUseCase,
        private val addProductUseCase: AddProductUseCase,
        private val removeProductUseCase: RemoveProductUseCase,
        private val getIfShoppingCartHasProductsUseCase: GetIfShoppingCartHasProductsUseCase,
        private val getSpecialProductUseCase: GetSpecialProductUseCase,
        private val navigator: Navigator
): ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    private val _shoppingCartWithProducts = MutableLiveData<Boolean>()
    private val _error = SingleLiveEvent<Int>()

    val uiState: LiveData<ProductDetailsUIState> = _uiState
    val shoppingCartWithProducts: LiveData<Boolean> = _shoppingCartWithProducts
    val error: LiveData<Int> = _error

    fun getProductDetails(productId: Long) {
        viewModelScope.launch {
            _shoppingCartWithProducts.postValue(getIfShoppingCartHasProductsUseCase.execute())
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
            val product = addProductUseCase.execute(productId, size)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product.toDetailsUI()))
        }
    }

    fun onRemoveClick(productId: Long, size: String) {
        viewModelScope.launch {
            val product = removeProductUseCase.execute(productId, size)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product.toDetailsUI()))
            _shoppingCartWithProducts.postValue(getIfShoppingCartHasProductsUseCase.execute())
        }
    }

    fun onShoppingCartClick(visible: Boolean) {
        if (visible) {
            navigator.navigateToFlow(NavigationFlow.ShoppingCart)
        } else {
            _error.postValue(R.string.products_shopping_cart_error)
        }
    }

    fun onConfirmButtonClick() {
        navigator.navigateToFlow(NavigationFlow.ShoppingCart)
    }
}