package com.jeff.pizza.login.presentation.usertype.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeff.pizza.login.domain.usecase.SetUserTypeUseCase
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUI
import com.jeff.pizza.login.presentation.usertype.model.UserTypeUIState
import com.jeff.pizza.login.presentation.usertype.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTypeViewModel @Inject constructor(
        private val setUserTypeUseCase: SetUserTypeUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<UserTypeUIState>()
    val uiState: LiveData<UserTypeUIState> = _uiState

    fun onSingleOptionClick() {
        _uiState.value = UserTypeUIState.Selected
    }

    fun onMarriedOptionClick() {
        _uiState.value = UserTypeUIState.Selected
    }

    fun onConfirmButtonClick(userTypeUI: UserTypeUI) {
        viewModelScope.launch {
            setUserTypeUseCase.execute(userTypeUI.toDomain())
        }
    }

}