package com.jeff.pizza.cart.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.cart.presentation.model.ShoppingCartProductUI
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.linhoapps.cart.databinding.ShoppingCartProductBinding

class ShoppingCartProductsAdapter(
        private val products: MutableList<ShoppingCartProductUI>
): BaseAdapter<ShoppingCartProductsAdapter.ViewHolder, ShoppingCartProductBinding, ShoppingCartProductUI>(products) {

    inner class ViewHolder(binding: ShoppingCartProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setBinding(ShoppingCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(binding) {
            titleProductShoppingCart.text = product.name
            pricesProductShoppingCart.adapter = ShoppingCartPricesAdapter(product.prices.toMutableList())
        }
    }

}