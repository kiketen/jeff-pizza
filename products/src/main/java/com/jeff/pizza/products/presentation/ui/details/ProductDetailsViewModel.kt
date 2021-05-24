package com.jeff.pizza.products.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.products.domain.usecase.AddProductUseCase
import com.jeff.pizza.products.domain.usecase.AddSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartHasProductsUseCase
import com.jeff.pizza.products.domain.usecase.GetProductUseCase
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.RemoveProductUseCase
import com.jeff.pizza.products.presentation.model.ProductDetailsUIState
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
        private val addSpecialProductUseCase: AddSpecialProductUseCase,
        private val navigator: Navigator
): ViewModel() {

    private val _uiState = MutableLiveData<ProductDetailsUIState>()
    private val _shoppingCartWithProducts = MutableLiveData<Boolean>()
    private val _error = SingleLiveEvent<Int>()
    private val _askForSpecialProduct = SingleLiveEvent<SpecialProduct>()

    val uiState: LiveData<ProductDetailsUIState> = _uiState
    val shoppingCartWithProducts: LiveData<Boolean> = _shoppingCartWithProducts
    val error: LiveData<Int> = _error
    val askForSpecialProduct: LiveData<SpecialProduct> = _askForSpecialProduct

    fun getProductDetails(productId: Long) {
        viewModelScope.launch {
            _shoppingCartWithProducts.postValue(getIfShoppingCartHasProductsUseCase.execute())
            val product = getProductUseCase.execute(productId)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product))
        }
    }

    fun onBackButtonClick() {
        _uiState.postValue(ProductDetailsUIState.Back)
    }

    fun onAddClick(productId: Long, size: String) {
        _shoppingCartWithProducts.postValue(true)
        viewModelScope.launch {
            val product = addProductUseCase.execute(productId, size)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product))
        }
    }

    fun onRemoveClick(productId: Long, size: String) {
        viewModelScope.launch {
            val product = removeProductUseCase.execute(productId, size)
            _uiState.postValue(ProductDetailsUIState.ShowingContent(product))
            _shoppingCartWithProducts.postValue(getIfShoppingCartHasProductsUseCase.execute())
        }
    }

    fun onShoppingCartClick(visible: Boolean) {
        if (visible) {
            proceedToGoToShoppingCart()
        } else {
            _error.postValue(R.string.products_shopping_cart_error)
        }
    }

    fun onConfirmButtonClick() {
        proceedToGoToShoppingCart()
    }

    fun onConfirmAddSpecialProductClick(specialProduct: SpecialProduct) {
        viewModelScope.launch {
            addSpecialProductUseCase.execute(specialProduct)
            goToShoppingCart()
        }
    }

    fun onCancelAddSpecialProductClick() {
        goToShoppingCart()
    }

    private fun proceedToGoToShoppingCart() {
        viewModelScope.launch {
            getSpecialProductUseCase.execute().either(
                    onSuccess = {
                        _askForSpecialProduct.postValue(it)
                    },
                    onError = {
                        goToShoppingCart()
                    }
            )
        }
    }

    private fun goToShoppingCart() {
        navigator.navigateToFlow(NavigationFlow.ShoppingCart)
    }
}