package com.jeff.pizza.products.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.core.presentation.extensions.loadImage
import com.jeff.pizza.core.presentation.model.ProductUI
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.R
import com.jeff.pizza.products.databinding.ProductItemBinding

class ProductsAdapter(
        private val products: MutableList<ProductUI>,
        private val onItemClicked: (Long) -> Unit
): BaseAdapter<ProductsAdapter.ViewHolder, ProductUI>(products) {

    inner class ViewHolder(binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        val titleProduct = binding.titleProduct
        val priceProduct = binding.priceProduct
        val imageProduct = binding.imageProduct
        val productCard = binding.productCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            titleProduct.text = product.name
            priceProduct.text = priceProduct.context.getString(R.string.product_price_since,
                    product.cheaperAmount.toString(), product.currency)
            imageProduct.loadImage(product.imageUrl)
            productCard.setSensitiveClickListener {
                onItemClicked(product.id)
            }
        }
    }

}