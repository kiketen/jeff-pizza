package com.jeff.pizza.products.presentation.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.pizza.core.presentation.extensions.invisible
import com.jeff.pizza.core.presentation.extensions.visible
import com.jeff.pizza.core.presentation.ui.BaseAdapter
import com.jeff.pizza.products.presentation.model.SizeUI
import com.linhoapps.products.R
import com.linhoapps.products.databinding.ProductPriceItemBinding

class ProductSizesAdapter(
        private val sizes: MutableList<SizeUI>,
        private val onAddClick: (String) -> Unit,
        private val onRemoveClick: (String) -> Unit
): BaseAdapter<ProductSizesAdapter.ViewHolder, ProductPriceItemBinding, SizeUI>(sizes) {

    inner class ViewHolder(binding: ProductPriceItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setBinding(ProductPriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productSize = sizes[position]
        with(binding) {
            sizeProductDetails.text = sizeProductDetails.context.getString(R.string.product_details_size, productSize.size)
            amountProductDetails.text = amountProductDetails.context.getString(R.string.product_details_amount, productSize.amount.toString())
            productsNumber.text = productSize.count.toString()
            handleRemoveButtonVisibility(productSize.count)
            addProductButton.setOnClickListener {
                addProduct(productSize)
            }
            removeProductButton.setOnClickListener {
                removeProduct(productSize)
            }
        }
    }

    private fun ProductPriceItemBinding.handleRemoveButtonVisibility(count: Int) {
        when (count) {
            0 -> removeProductButton.invisible()
            else -> removeProductButton.visible()
        }
    }

    private fun ProductPriceItemBinding.addProduct(productSize: SizeUI) {
        val increasedProductNumber = productsNumber.text.toString().toInt() + 1
        updateProductsNumberText(increasedProductNumber)
        onAddClick(productSize.size)
    }

    private fun ProductPriceItemBinding.removeProduct(productSize: SizeUI) {
        val decreasedProductNumber = productsNumber.text.toString().toInt() - 1
        updateProductsNumberText(decreasedProductNumber)
        onRemoveClick(productSize.size)
    }

    private fun ProductPriceItemBinding.updateProductsNumberText(increasedProductNumber: Int) {
        productsNumber.text = increasedProductNumber.toString()
        handleRemoveButtonVisibility(increasedProductNumber)
    }

}