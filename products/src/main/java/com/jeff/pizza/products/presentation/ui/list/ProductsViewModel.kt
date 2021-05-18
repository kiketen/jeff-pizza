package com.jeff.pizza.products.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.core.domain.usecase.GetProductsUseCase
import com.jeff.pizza.core.presentation.ui.SingleLiveEvent
import com.jeff.pizza.products.presentation.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductsUIState>()
    private val _navigation = SingleLiveEvent<ProductsNavigation>()
    val uiState: LiveData<ProductsUIState> = _uiState
    val navigation: LiveData<ProductsNavigation> = _navigation

    init {
        getProducts(refresh = false)
    }

    fun onRetryClick() {
        getProducts(refresh = true)
    }

    fun onProductClicked(productId: Long) {
        _navigation.postValue(ProductsNavigation.ProductDetails(productId))
    }

    private fun getProducts(refresh: Boolean) {
        _uiState.postValue(ProductsUIState.Loading)
        viewModelScope.launch {
            getProductsUseCase.execute(refresh).either(
                onSuccess = {
                    _uiState.postValue(ProductsUIState.ShowingContent(it.toUI()))
                },
                onError = {
                    _uiState.postValue(ProductsUIState.Error)
                }
            )
        }
    }

}