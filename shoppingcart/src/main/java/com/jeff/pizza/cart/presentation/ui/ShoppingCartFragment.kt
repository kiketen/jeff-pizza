package com.jeff.pizza.cart.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeff.pizza.cart.presentation.model.ShoppingCartInfoUI
import com.jeff.pizza.cart.presentation.model.ShoppingCartUIState
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.linhoapps.cart.R
import com.linhoapps.cart.databinding.ShoppingCartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment: BaseFragment<ShoppingCartFragmentBinding>() {

    private val viewModel: ShoppingCartViewModel by viewModels()

    private var productsAdapter = ShoppingCartProductsAdapter(
            products = mutableListOf()
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setBinding(ShoppingCartFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
        setViewModelObservers()
    }

    private fun setLayout() {
        binding.productsShoppingCart.adapter = productsAdapter
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
        }
    }

    private fun renderUIState(shoppingCartUIState: ShoppingCartUIState) {
        when (shoppingCartUIState) {
            is ShoppingCartUIState.ShowingContent -> showShoppingCart(shoppingCartUIState.shoppingCartInfoUI)
        }
    }

    private fun showShoppingCart(shoppingCartInfoUI: ShoppingCartInfoUI) {
        productsAdapter.updateItems(shoppingCartInfoUI.products)
        with(binding) {
            shoppingCartInfoUI.specialProduct?.let {
                specialProductShoppingCart.contentShoppingCart.text = getString(R.string.shopping_cart_product_info, it.count, it.text)
                specialProductShoppingCart.priceShoppingCart.text = getString(R.string.product_details_amount, it.amount.toString())
            }
            totalAmountShoppingCart.text = getString(R.string.shopping_cart_total_amount, shoppingCartInfoUI.totalAmount.toString())
        }
    }
}


