package com.example.viewmodel.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.entity.categories.Category
import com.example.entity.products.Product
import com.example.entity.utils.BadTokenException
import com.example.usecase.useCases.Authentiaction.UserApiKeyUseCase
import com.example.usecase.useCases.categories.GetAllCategoriesUseCase
import com.example.usecase.useCases.products.AddProductToFavoriteUseCase
import com.example.usecase.useCases.products.DeleteFromFavoriteUseCase
import com.example.usecase.useCases.products.GetProductsUseCase
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.StringsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val userApiKeyUseCase: UserApiKeyUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToFavoriteUseCase: AddProductToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val stringsProvider: StringsProvider,
) :
    BaseViewModel<HomeUiState, HomeEffect>(HomeUiState()), HomeInteraction {
    init {
        viewModelScope.launch {
            userApiKeyUseCase.getUserApiKey().collectLatest { token ->
                _state.update { it.copy(isSignedIn = token != "") }
                Log.i("TOKEN", token)
            }
        }
        getAllCategories()
        getProducts()
    }

    fun getAllCategories() {
        tryToExecute({ getAllCategoriesUseCase() }, ::onGetCategoriesSuccess, ::onGetCategoriesFail)
    }

    private fun onGetCategoriesFail(throwable: Throwable) {}
    private fun onGetCategoriesSuccess(list: List<Category>) {
        _state.update { it.copy(categories = list.toCategory()) }
    }

    override fun onItemClick(itemId: String) {

    }

    override fun onTabClick(categoryId: String?) {
        getProducts(categoryId)
    }

    override fun onFavoriteClick(itemId: String, isFavorite: Boolean) {
        if (!state.value.isSignedIn) {
            updateState { copy(message = "not signed in") }
            return
        }
        if (isFavorite)
            tryToExecute(
                { deleteFromFavoriteUseCase(itemId) },
                { onDeleteFavoriteSuccess(itemId) },
                {})
        else
            tryToExecute({ addProductToFavoriteUseCase(itemId) }, ::onAddFavoriteSuccess, ::onError)

    }

    private fun onDeleteFavoriteSuccess(itemId: String) {
        _state.update {
            it.copy(items = state.value.items.map { homeCard ->
                if (homeCard.itemId == itemId)
                    homeCard.copy(isFavorite = false)
                else
                    homeCard
            })
        }
    }

    private fun onError(error: Throwable) {
        _state.update { it.copy(message = stringsProvider.noInternetError, isLoading = false) }
    }

    private fun onAddFavoriteSuccess(itemId: String) {
        _state.update {
            it.copy(isLoading = false, items = state.value.items.map { homeCard ->
                if (homeCard.itemId == itemId) {
                    Log.i("HENA", "THIS IS IT")
                    homeCard.copy(isFavorite = true)
                } else {
                    Log.i("HMMM", "NOT IT ---- ${homeCard.itemId}")
                    homeCard
                }
            }

            )

        }

    }

    private fun getProducts(categoryId: String? = null) {
        tryToExecute({ getProductsUseCase(categoryId) }, ::onProductsSuccess, ::onProductsFail)
    }

    private fun onProductsSuccess(products: List<Product>) {
        _state.update { it.copy(isLoading = false, items = products.toItemCard()) }
    }

    private fun onProductsFail(throwable: Throwable) {
        when (throwable) {
            is BadTokenException -> {
                viewModelScope.launch {
                    userApiKeyUseCase.setUserApiKey("")
                    sendUiEffect(HomeEffect.NavigateToLogIn)
                }.also {
                    return
                }
            }
        }
        Log.d("exception", throwable.message.toString())
        _state.update { it.copy(isLoading = false, message = throwable.message.toString()) }
    }

    fun clearMessage() {
        _state.update { it.copy(message = null) }
    }
}