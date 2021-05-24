package com.jeff.pizza.products.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.model.products.SpecialProduct
import com.jeff.pizza.core.domain.usecase.GetProductsUseCase
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.navigation.NavigationFlow
import com.jeff.pizza.navigation.Navigator
import com.jeff.pizza.products.domain.usecase.AddSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.GetIfShoppingCartHasProductsUseCase
import com.jeff.pizza.products.domain.usecase.GetSpecialProductUseCase
import com.jeff.pizza.products.domain.usecase.LogoutUseCase
import com.jeff.pizza.products.presentation.model.ProductsError
import com.jeff.pizza.products.presentation.model.ProductsNavigation
import com.jeff.pizza.products.presentation.model.ProductsUIState
import com.jeff.pizza.products.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
        private val getProductsUseCase: GetProductsUseCase,
        private val getIfShoppingCartHasProductsUseCase: GetIfShoppingCartHasProductsUseCase,
        private val getSpecialProductUseCase: GetSpecialProductUseCase,
        private val addSpecialProductUseCase: AddSpecialProductUseCase,
        private val logoutUseCase: LogoutUseCase,
        private val navigator: Navigator
): ViewModel() {

    private val _uiState = MutableLiveData<ProductsUIState>()
    private val _navigation = SingleLiveEvent<ProductsNavigation>()
    private val _showShoppingCartNotification = MutableLiveData<Boolean>()
    private val _error = SingleLiveEvent<ProductsError>()
    private val _askForSpecialProduct = SingleLiveEvent<SpecialProduct>()

    val uiState: LiveData<ProductsUIState> = _uiState
    val navigation: LiveData<ProductsNavigation> = _navigation
    val showShoppingCartNotification: LiveData<Boolean> = _showShoppingCartNotification
    val error: LiveData<ProductsError> = _error
    val askForSpecialProduct: LiveData<SpecialProduct> = _askForSpecialProduct

    init {
        getProducts(refresh = false)
    }

    fun getShoppingCartNotificationVisibility() {
        viewModelScope.launch {
            _showShoppingCartNotification.postValue(getIfShoppingCartHasProductsUseCase.execute())
        }
    }

    fun onRetryClick() {
        getProducts(refresh = true)
    }

    fun onProductClicked(productId: Long) {
        _navigation.postValue(ProductsNavigation.ProductDetails(productId))
    }

    fun onShoppingCartClick(visible: Boolean) {
        if (visible) {
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
        } else {
            _error.postValue(ProductsError.ErrorShort(R.string.products_shopping_cart_error))
        }
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

    fun onLogoutClick() {
        viewModelScope.launch {
            logoutUseCase.execute()
            navigator.navigateToFlow(NavigationFlow.Login)
        }
    }

    private fun getProducts(refresh: Boolean) {
        _uiState.postValue(ProductsUIState.Loading)
        viewModelScope.launch {
            getProductsUseCase.execute(refresh).either(
                    onSuccess = {
                        _uiState.postValue(ProductsUIState.ShowingContent(it))
                    },
                    onError = {
                        _error.postValue(ProductsError.ErrorIndefinite(R.string.unexpected_error))
                    }
            )
        }
    }

    private fun goToShoppingCart() {
        navigator.navigateToFlow(NavigationFlow.ShoppingCart)
    }
}