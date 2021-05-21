package com.jeff.pizza.products.presentation.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.jeff.pizza.core.presentation.extensions.isVisible
import com.jeff.pizza.core.presentation.extensions.loadImage
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.extensions.switchVisibilityAnimated
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.core.presentation.utils.setSensitiveClickListener
import com.jeff.pizza.products.presentation.model.ProductDetailsUI
import com.jeff.pizza.products.presentation.model.ProductDetailsUIState
import com.linhoapps.products.databinding.ProductDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment: BaseFragment<ProductDetailsFragmentBinding>() {

    private val viewModel: ProductDetailsViewModel by viewModels()
    private val args: ProductDetailsFragmentArgs by navArgs()

    private var productPricesAdapter = ProductPricesAdapter(
            prices = mutableListOf(),
            onAddClick = { size -> viewModel.onAddClick(args.productId, size) },
            onRemoveClick = { size -> viewModel.onRemoveClick(args.productId, size) }
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
        with(binding) {
            backButtonProductDetails.setSensitiveClickListener {
                viewModel.onBackButtonClick()
            }
            shoppingCartProductDetails.cartButton.setSensitiveClickListener {
                viewModel.onShoppingCartClick(shoppingCartProductDetails.cartNotification.isVisible())
            }
            confirmButtonProductDetails.setSensitiveClickListener {
                viewModel.onConfirmButtonClick()
            }

        }
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
            observe(viewModel.shoppingCartWithProducts, ::handleShoppingCartWithProducts)
            observe(viewModel.error, ::showError)
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
            productPricesAdapter.updateItems(details.prices)
        }
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

    private fun handleShoppingCartWithProducts(visible: Boolean) {
        with(binding) {
            shoppingCartProductDetails.cartNotification.switchVisibilityAnimated(visible)
            confirmButtonProductDetails.switchVisibilityAnimated(visible)
        }
    }

    private fun showError(@StringRes textId: Int) {
        with(binding) {
            Snackbar.make(root, textId, Snackbar.LENGTH_SHORT).show()
        }
    }
}