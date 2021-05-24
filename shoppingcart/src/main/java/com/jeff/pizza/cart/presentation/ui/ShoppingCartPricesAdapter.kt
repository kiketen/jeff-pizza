package com.jeff.pizza.cart.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.cart.presentation.model.ShoppingCartPriceUI
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.linhoapps.cart.R
import com.linhoapps.cart.databinding.ShoppingCartPriceBinding

class ShoppingCartPricesAdapter(
        private val prices: MutableList<ShoppingCartPriceUI>
): BaseAdapter<ShoppingCartPricesAdapter.ViewHolder, ShoppingCartPriceBinding, ShoppingCartPriceUI>(prices) {

    inner class ViewHolder(binding: ShoppingCartPriceBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setBinding(ShoppingCartPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val price = prices[position]
        with(binding) {
            contentShoppingCart.text = contentShoppingCart.context.getString(
                    R.string.shopping_cart_product_info, price.count, price.text
            )
            priceShoppingCart.text = contentShoppingCart.context.getString(R.string.product_details_amount,
                    price.amount.toString(), price.currency)
        }
    }

}