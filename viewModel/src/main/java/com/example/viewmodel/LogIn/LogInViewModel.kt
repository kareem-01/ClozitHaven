package com.example.viewmodel.LogIn

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.usecase.UseCases.Authentiaction.LogInUseCase
import com.example.usecase.UseCases.Authentiaction.UserApiKeyUseCase
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.StringsProvider
import com.example.viewmodel.signUp.TextType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val stringsProvider: StringsProvider,
    private val userApiKeyUseCase: UserApiKeyUseCase,
) :
    BaseViewModel<LogInUiState,LogInEffect>(LogInUiState()),
    LogInInteraction {
    override fun updateUiState(type: TextType, text: String) {

    }

    override fun logIn() {
        if (checkToLogIn())
            tryToExecute({ logInUseCase(state.value.toBody()) }, ::onSuccess, ::onError)

    }

    private fun checkToLogIn(): Boolean {
        state.value.let {
            return if (it.email.isBlank() || it.password.isBlank()) {
             _state.update { it.copy(message = stringsProvider.emptyFields) }
                false
            }else if (it.password.length < 6) {
                _state.update { it.copy(message = stringsProvider.lessThanSixPassword) }
                false
            } else
                true
        }
    }

    private fun onSuccess(token: String) {
        Log.i("SUCCESS",token)
        viewModelScope.launch {
            userApiKeyUseCase.setUserApiKey(token)
        }
    }

    private fun onError(e: Exception) {
        _state.update { it.copy(message = stringsProvider.incorrectEmailOrPassword) }
    }

    override fun updateAllStates(email: String, password: String) {
        _state.update { it.copy(email = email, password = password) }
    }

    override fun clearMessage() {
        _state.update { it.copy(message = null) }
    }

    override fun onLogInClick() {
        sendUiEffect(LogInEffect.NavigateToLogIn)
    }

}