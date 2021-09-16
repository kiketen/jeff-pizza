package com.jeff.pizza.cart.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.cart.databinding.ShoppingCartProductBinding
import com.jeff.pizza.cart.presentation.model.ShoppingCartProductUI
import com.jeff.pizza.core.presentation.ui.BaseAdapter

class ShoppingCartProductsAdapter(
        private val products: MutableList<ShoppingCartProductUI>
): BaseAdapter<ShoppingCartProductsAdapter.ViewHolder, ShoppingCartProductUI>(products) {

    inner class ViewHolder(binding: ShoppingCartProductBinding): RecyclerView.ViewHolder(binding.root) {
        val titleProductShoppingCart = binding.titleProductShoppingCart
        val pricesProductShoppingCart = binding.pricesProductShoppingCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoppingCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            titleProductShoppingCart.text = product.name
            pricesProductShoppingCart.adapter = ShoppingCartPricesAdapter(product.prices.toMutableList())
        }
    }

}