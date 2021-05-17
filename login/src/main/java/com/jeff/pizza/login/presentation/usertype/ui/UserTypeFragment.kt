package com.jeff.pizza.login.presentation.usertype.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.jeff.pizza.core.presentation.extensions.observe
import com.jeff.pizza.core.presentation.ui.BaseFragment
import com.jeff.pizza.login.R
import com.jeff.pizza.login.databinding.UserTypeFragmentBinding
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUI
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserTypeFragment: BaseFragment<UserTypeFragmentBinding>() {

    private val viewModel: UserTypeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setBinding(UserTypeFragmentBinding.inflate(inflater, container, false))
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
            confirmButtonLogin.setOnClickListener {
                viewModel.onConfirmButtonClick(getUserType())
            }
        }

    }

    private fun renderUIState(uiState: UserTypeUIState) {
        with(binding) {
            when (uiState) {
                UserTypeUIState.Unselected -> confirmButtonLogin.isEnabled = false
                UserTypeUIState.Selected -> confirmButtonLogin.isEnabled = true
                UserTypeUIState.Loading -> confirmButtonLogin.isLoading = true
                UserTypeUIState.Error -> showErrorState()
            }
        }
    }

    private fun getUserType(): UserTypeUI {
        with(binding) {
            return when {
                singleOptionLogin.isChecked -> UserTypeUI.SINGLE
                marriedOptionLogin.isChecked -> UserTypeUI.MARRIED
                else -> UserTypeUI.UNKNOWN
            }
        }
    }

    private fun showErrorState() {
        with(binding) {
            confirmButtonLogin.isLoading = false
            Snackbar.make(root, R.string.login_error, Snackbar.LENGTH_SHORT).show()
        }
    }

}


