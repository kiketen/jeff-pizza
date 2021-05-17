package com.jeff.pizza.products.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.core.presentation.extensions.loadImage
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.presentation.model.ProductUI
import com.linhoapps.products.R
import com.linhoapps.products.databinding.ProductItemBinding

class ProductsAdapter(
        private val products: MutableList<ProductUI>,
        private val onItemClicked: (Long) -> Unit
): BaseAdapter<ProductsAdapter.ViewHolder, ProductItemBinding, ProductUI>(products) {

    inner class ViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        setBinding(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        with(binding) {
            titleProduct.text = product.name
            priceProduct.text = priceProduct.context.getString(R.string.product_price_since, product.cheaperAmount.toString())
            imageProduct.loadImage(product.imageUrl)
            productCard.setSensitiveClickListener {
                onItemClicked(product.id)
            }
        }
    }

}