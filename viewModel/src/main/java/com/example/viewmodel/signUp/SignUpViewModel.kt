package com.example.viewmodel.signUp

import androidx.lifecycle.viewModelScope
import com.example.usecase.UseCases.Authentiaction.SignUpUseCase
import com.example.usecase.UseCases.Authentiaction.UserApiKeyUseCase
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.StringsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val stringsProvider: StringsProvider,
    private val userApiKeyUseCase: UserApiKeyUseCase,
) :
    BaseViewModel<SignUpUiState, SignUpEffect>(SignUpUiState()), SignUpInteraction {

    override fun signUp() {

        if (checkToSignUp()) {
            clearMessage()
            tryToExecute(
                { signUpUseCase(state.value.toSignUpBody()) },
                ::onSignUpSuccess,
                ::onError
            )
            clearMessage()
        } else
            clearMessage()
    }

    private fun checkToSignUp(): Boolean {
        clearMessage()
        state.value.let {
            return if (it.email.isEmpty() || it.userName.isEmpty() || it.password.isEmpty() || it.confirmPassword.isEmpty() || it.phoneNumber.isEmpty()) {
                _state.update { it.copy(message = stringsProvider.emptyFields) }
                false
            } else if (it.userName.length < 3) {
                _state.update { it.copy(message = stringsProvider.userNameLessThanThree) }
                false
            } else if (it.password.length < 6) {
                _state.update { it.copy(message = stringsProvider.lessThanSixPassword) }
                false
            } else if (!it.password.equals(it.confirmPassword)) {
                _state.update { it.copy(message = stringsProvider.passwordAndConfirmationNotTheSame) }
                false
            } else if (
                it.phoneNumber.take(3) != "012" &&
                it.phoneNumber.take(3) != "010" &&
                it.phoneNumber.take(3) != "011" &&
                it.phoneNumber.take(3) != "015"
            ) {
                _state.update { it.copy(message = stringsProvider.phoneNumberNotEgyptian) }
                false
            } else if (it.phoneNumber.length != 11) {
                _state.update { it.copy(message = stringsProvider.phoneNumberLessThan11) }
                false
            } else {
                true
            }
        }
    }

    private fun clearMessage() {
        viewModelScope.launch {
            delay(3000)
            _state.update { it.copy(message = null) }
        }
    }

    private fun onSignUpSuccess(message: String) {
        _state.update { it.copy(message = stringsProvider.accountCreated) }
        viewModelScope.launch {
            userApiKeyUseCase.setUserApiKey(message)
        }
    }

    private fun onError(e: Exception) {
        _state.update { it.copy(message = stringsProvider.invalidEmail) }
        clearMessage()
    }

    override fun updateAllStates(
        userName: String,
        email: String,
        password: String,
        rePassword: String,
        phoneNumber: String
    ) {
        _state.update {
            it.copy(
                userName = userName,
                email = email,
                password = password,
                confirmPassword = rePassword,
                phoneNumber = phoneNumber
            )
        }
    }

    override fun onLogInClick() {
        sendUiEffect(SignUpEffect.NavigateToLogIn)
    }

    override fun updateUiState(type: TextType, text: String) {
        when (type) {
            TextType.USERNAME -> _state.update { it.copy(userName = text) }
            TextType.EMAIL -> _state.update { it.copy(email = text) }
            TextType.PASSWORD -> _state.update { it.copy(password = text) }
            TextType.PHONE -> _state.update { it.copy(phoneNumber = text) }
            TextType.CONFIRMPASSWORD -> _state.update { it.copy(confirmPassword = text) }
        }
    }
}