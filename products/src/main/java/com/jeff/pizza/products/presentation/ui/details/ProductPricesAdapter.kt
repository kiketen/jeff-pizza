package com.jeff.pizza.products.presentation.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.presentation.model.PriceUI
import com.linhoapps.products.R
import com.linhoapps.products.databinding.ProductPriceItemBinding

class ProductPricesAdapter(
        private val prices: MutableList<PriceUI>
): BaseAdapter<ProductPricesAdapter.ViewHolder, ProductPriceItemBinding, PriceUI>(prices) {

    inner class ViewHolder(binding: ProductPriceItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setBinding(ProductPriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val price = prices[position]
        with(binding) {
            sizeProductDetails.text = sizeProductDetails.context.getString(R.string.product_details_size, price.size)
            amountProductDetails.text = amountProductDetails.context.getString(R.string.product_details_amount, price.amount.toString())
        }
    }

}