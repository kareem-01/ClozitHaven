package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.UseCases.categories.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCategoriesUseCase: GetAllCategoriesUseCase) :
    ViewModel() {
    init {
        getAllCategories()
    }

    fun getAllCategories() {
        viewModelScope.launch {
            val result = getAllCategoriesUseCase()
            Log.i("DATA", result.toString())
        }

    }
}