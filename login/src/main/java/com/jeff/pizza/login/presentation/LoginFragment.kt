package com.jeff.pizza.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jeff.pizza.core.extensions.observe
import com.jeff.pizza.core.ui.BaseFragment
import com.jeff.pizza.login.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    private val confirmButton
        get() = binding.confirmButtonLogin

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(LoginFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
        setLayoutListeners()
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {
            observe(viewModel.uiState, ::renderUIState)
        }
    }

    private fun setLayoutListeners() {
        with(binding) {
            singleOptionLogin.setOnClickListener {
                viewModel.onSingleOptionClick()
            }
            marriedOptionLogin.setOnClickListener {
                viewModel.onMarriedOptionClick()
            }
        }
    }

    private fun renderUIState(uiState: LoginUIState) {
        when (uiState) {
            LoginUIState.Unselected -> confirmButton.isEnabled = false
            LoginUIState.Selected -> confirmButton.isEnabled = true
            LoginUIState.Loading -> confirmButton.isLoading = true
        }
    }

}


