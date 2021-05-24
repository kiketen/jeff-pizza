package com.jeff.pizza.cart.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeff.pizza.cart.presentation.model.ShoppingCartInfoUI
import com.jeff.pizza.cart.presentation.model.ShoppingCartUIState
import com.jeff.pizza.core.presentation.extensions.gone
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.extensions.visible
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
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
        setListeners()
        setViewModelObservers()
    }

    private fun setLayout() {
        binding.productsShoppingCart.adapter = productsAdapter
    }

    private fun setListeners() {
        binding.confirmButtonShoppingCart.setSensitiveClickListener {
            viewModel.onConfirmButtonClick()
        }
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
            if (shoppingCartInfoUI.specialProduct == null) {
                specialProductShoppingCart.contentShoppingCart.gone()
                specialProductShoppingCart.priceShoppingCart.gone()
            } else {
                specialProductShoppingCart.contentShoppingCart.visible()
                specialProductShoppingCart.priceShoppingCart.visible()
                specialProductShoppingCart.contentShoppingCart.text = getString(R.string.shopping_cart_product_info,
                        shoppingCartInfoUI.specialProduct.count, shoppingCartInfoUI.specialProduct.text)
                specialProductShoppingCart.priceShoppingCart.text = getString(R.string.product_details_amount,
                        shoppingCartInfoUI.specialProduct.amount.toString(), shoppingCartInfoUI.specialProduct.currency)
            }
            totalAmountShoppingCart.text = getString(R.string.shopping_cart_total_amount,
                    shoppingCartInfoUI.totalAmount.toString(), shoppingCartInfoUI.currency)
        }
    }
}


