package com.jeff.pizza.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeff.pizza.cart.model.ShoppingCartUIState
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.linhoapps.cart.databinding.ShoppingCartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment: BaseFragment<ShoppingCartFragmentBinding>() {

    private val viewModel: ShoppingCartViewModel by viewModels()

    private var productsAdapter = ShoppingCartProductsAdapter(
            products = mutableListOf()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(ShoppingCartFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
        setViewModelObservers()
    }

    private fun setLayout() {
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
        }
    }

    private fun renderUIState(shoppingCartUIState: ShoppingCartUIState) {
        when (shoppingCartUIState) {
            is ShoppingCartUIState.ShowingContent -> showShoppingCart()
        }
    }

    private fun showShoppingCart() {
    }
}


