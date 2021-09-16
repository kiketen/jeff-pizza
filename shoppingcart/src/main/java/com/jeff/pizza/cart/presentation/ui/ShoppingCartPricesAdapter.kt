package com.jeff.pizza.cart.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.cart.R
import com.jeff.pizza.cart.databinding.ShoppingCartPriceBinding
import com.jeff.pizza.cart.presentation.model.ShoppingCartPriceUI
import com.jeff.pizza.core.presentation.ui.BaseAdapter

class ShoppingCartPricesAdapter(
        private val prices: MutableList<ShoppingCartPriceUI>
): BaseAdapter<ShoppingCartPricesAdapter.ViewHolder, ShoppingCartPriceUI>(prices) {

    inner class ViewHolder(binding: ShoppingCartPriceBinding): RecyclerView.ViewHolder(binding.root) {
        val contentShoppingCart = binding.contentShoppingCart
        val priceShoppingCart = binding.priceShoppingCart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoppingCartPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val price = prices[position]
        with(holder) {
            contentShoppingCart.text = contentShoppingCart.context.getString(
                    R.string.shopping_cart_product_info, price.count, price.text
            )
            priceShoppingCart.text = contentShoppingCart.context.getString(R.string.product_details_amount,
                    price.amount.toString(), price.currency)
        }
    }

}