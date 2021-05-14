package com.jeff.pizza.splash.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.jeff.pizza.core.presentation.extensions.gone
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.extensions.visible
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.splash.presentation.model.SplashUIState
import com.linhoapps.splash.R
import com.linhoapps.splash.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: BaseFragment<SplashFragmentBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(SplashFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
        }
    }

    private fun renderUIState(splashUIState: SplashUIState) {
        when (splashUIState) {
            SplashUIState.Loading -> binding.progressSplash.visible()
            SplashUIState.Error -> showErrorState()
        }
    }

    private fun showErrorState() {
        with(binding) {
            progressSplash.gone()
            val snack = Snackbar.make(root, R.string.unexpected_error, Snackbar.LENGTH_INDEFINITE)
            snack.setAction(R.string.retry) {
                viewModel.onRetryClick()
            }
            snack.show()
        }
    }
}


