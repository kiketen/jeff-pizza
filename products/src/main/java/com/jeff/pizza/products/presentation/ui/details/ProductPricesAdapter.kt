package com.jeff.pizza.products.presentation.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.core.presentation.extensions.invisible
import com.jeff.pizza.core.presentation.extensions.visible
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.jeff.pizza.products.presentation.model.PriceUI
import com.linhoapps.products.R
import com.linhoapps.products.databinding.ProductPriceItemBinding

class ProductPricesAdapter(
        private val prices: MutableList<PriceUI>,
        private val onAddClick: (String) -> Unit,
        private val onRemoveClick: (String) -> Unit
): BaseAdapter<ProductPricesAdapter.ViewHolder, ProductPriceItemBinding, PriceUI>(prices) {

    inner class ViewHolder(binding: ProductPriceItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setBinding(ProductPriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productPrice = prices[position]
        with(binding) {
            sizeProductDetails.text = sizeProductDetails.context.getString(R.string.product_details_size, productPrice.size)
            amountProductDetails.text = amountProductDetails.context.getString(R.string.product_details_amount,
                    productPrice.amount.toString(), productPrice.currency)
            productsNumber.text = productPrice.count.toString()
            handleRemoveButtonVisibility(productPrice.count)
            addProductButton.setOnClickListener {
                addProduct(productPrice)
            }
            removeProductButton.setOnClickListener {
                removeProduct(productPrice)
            }
        }
    }

    private fun ProductPriceItemBinding.handleRemoveButtonVisibility(count: Int) {
        when (count) {
            0 -> removeProductButton.invisible()
            else -> removeProductButton.visible()
        }
    }

    private fun ProductPriceItemBinding.addProduct(productPrice: PriceUI) {
        val increasedProductNumber = productsNumber.text.toString().toInt() + 1
        updateProductsNumberText(increasedProductNumber)
        onAddClick(productPrice.size)
    }

    private fun ProductPriceItemBinding.removeProduct(productPrice: PriceUI) {
        val decreasedProductNumber = productsNumber.text.toString().toInt() - 1
        updateProductsNumberText(decreasedProductNumber)
        onRemoveClick(productPrice.size)
    }

    private fun ProductPriceItemBinding.updateProductsNumberText(increasedProductNumber: Int) {
        productsNumber.text = increasedProductNumber.toString()
        handleRemoveButtonVisibility(increasedProductNumber)
    }

}