package com.jeff.pizza.cart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.cart.model.ShoppingCartProductUI
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

    }

}