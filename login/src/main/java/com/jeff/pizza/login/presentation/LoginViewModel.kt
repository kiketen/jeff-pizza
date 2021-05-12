package com.jeff.pizza.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableLiveData<LoginUIState>()
    val uiState: LiveData<LoginUIState> = _uiState

    fun onSingleOptionClick() {
        _uiState.value = LoginUIState.Selected
    }

    fun onMarriedOptionClick() {
        _uiState.value = LoginUIState.Selected
    }

}