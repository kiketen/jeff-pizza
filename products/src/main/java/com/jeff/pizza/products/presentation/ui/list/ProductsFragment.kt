package com.jeff.pizza.products.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jeff.pizza.core.presentation.extensions.gone
import com.jeff.pizza.core.presentation.extensions.isVisible
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.extensions.switchVisibilityAnimated
import com.jeff.pizza.core.presentation.extensions.visible
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.presentation.model.ProductUI
import com.jeff.pizza.products.presentation.model.ProductsError
import com.jeff.pizza.products.presentation.model.ProductsNavigation
import com.jeff.pizza.products.presentation.model.ProductsUIState
import com.linhoapps.products.R
import com.linhoapps.products.databinding.ProductsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: BaseFragment<ProductsFragmentBinding>() {

    private val viewModel: ProductsViewModel by viewModels()

    private var productsAdapter = ProductsAdapter(
            products = mutableListOf(),
            onItemClicked = {
                viewModel.onProductClicked(it)
            }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(ProductsFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShoppingCartNotificationVisibility()
        setLayout()
        setListeners()
        setViewModelObservers()
    }

    private fun setLayout() {
        binding.listProducts.adapter = productsAdapter
    }

    private fun setListeners() {
        binding.shoppingCartProducts.cartButton.setSensitiveClickListener {
            viewModel.onShoppingCartClick(binding.shoppingCartProducts.cartNotification.isVisible())
        }
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
            observe(viewModel.navigation, ::handleNavigation)
            observe(viewModel.showShoppingCartNotification, ::handleShoppingCartNotification)
            observe(viewModel.error, ::renderError)
        }
    }

    private fun renderUIState(productsUIState: ProductsUIState) {
        when (productsUIState) {
            ProductsUIState.Loading -> binding.progressProducts.visible()
            is ProductsUIState.ShowingContent -> showProducts(productsUIState.products)
        }
    }

    private fun showProducts(products: List<ProductUI>) {
        binding.progressProducts.gone()
        productsAdapter.updateItems(products)
    }

    private fun renderError(error: ProductsError) {
        when (error) {
            is ProductsError.ErrorIndefinite -> showErrorIndefinite(error.textId)
            is ProductsError.ErrorShort -> showErrorShort(error.textId)
        }
    }

    private fun showErrorIndefinite(textId: Int) {
        with(binding) {
            progressProducts.gone()
            val snack = Snackbar.make(root, textId, Snackbar.LENGTH_INDEFINITE)
            snack.setAction(R.string.retry) {
                viewModel.onRetryClick()
            }
            snack.show()
        }
    }

    private fun showErrorShort(textId: Int) {
        with(binding) {
            progressProducts.gone()
            Snackbar.make(root, textId, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleNavigation(productsNavigation: ProductsNavigation) {
        when (productsNavigation) {
            is ProductsNavigation.ProductDetails -> goToProductDetails(productsNavigation.productId)
        }
    }

    private fun goToProductDetails(productId: Long) {
        val action = ProductsFragmentDirections.productsToProductDetails(productId)
        findNavController().navigate(action)
    }

    private fun handleShoppingCartNotification(showNotification: Boolean) {
        binding.shoppingCartProducts.cartNotification.switchVisibilityAnimated(showNotification)
    }
}


