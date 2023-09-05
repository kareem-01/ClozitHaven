package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.utils.BadEmailException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE>(initialValue: STATE) : ViewModel() {
    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialValue)
    val state = _state.asStateFlow()
    fun <T> tryToExecute(
        request: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                request().also(onSuccess)
            } catch (e: BadEmailException) {
                onError(e)
            }catch (e: Exception) {
                onError(e)
            }
        }
    }
}