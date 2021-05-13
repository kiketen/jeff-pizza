package com.jeff.pizza.splash.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.splash.presentation.model.SplashNavigation
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
            observe(viewModel.navigator, ::handleNavigation)
        }
    }

    private fun handleNavigation(navigation: SplashNavigation) {
        when (navigation) {
            SplashNavigation.Login -> goToLogin()
        }
    }

    private fun goToLogin() {

    }

}


