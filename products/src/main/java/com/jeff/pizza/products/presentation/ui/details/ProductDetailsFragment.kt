package com.jeff.pizza.products.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jeff.pizza.core.presentation.extensions.loadImage
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.linhoapps.products.databinding.ProductDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment: BaseFragment<ProductDetailsFragmentBinding>() {

    private val viewModel: ProductDetailsViewModel by viewModels()
    private val args: ProductDetailsFragmentArgs by navArgs()

    private var productPricesAdapter = ProductSizesAdapter(
            sizes = mutableListOf(),
            onAddClick = { size -> viewModel.onAddClick(size) },
            onRemoveClick = { size -> viewModel.onRemoveClick(size) }
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(ProductDetailsFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
        setListeners()
        setViewModelObservers()
    }

    private fun setLayout() {
        viewModel.getProductDetails(args.productId)
        binding.pricesProductDetails.adapter = productPricesAdapter
    }

    private fun setListeners() {
        binding.backButtonProductDetails.setSensitiveClickListener {
            viewModel.onBackButtonClick()
        }
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
        }
    }

    private fun renderUIState(productDetailsUIState: ProductDetailsUIState) {
        when (productDetailsUIState) {
            is ProductDetailsUIState.ShowingContent -> showDetails(productDetailsUIState.details)
            is ProductDetailsUIState.Back -> goBack()
        }
    }

    private fun showDetails(details: ProductDetailsUI) {
        with(binding) {
            imageProductDetails.loadImage(details.imageUrl)
            titleProductDetails.text = details.name
            contentProductDetails.text = details.content
            productPricesAdapter.updateItems(details.sizes)
        }
    }

    private fun goBack() {
        findNavController().popBackStack()
    }
}