package com.example.viewmodel.details

import android.util.Log
import com.example.entity.products.Product
import com.example.entity.utils.BadTokenException
import com.example.usecase.useCases.products.AddProductToCartUseCase
import com.example.usecase.useCases.products.AddProductToFavoriteUseCase
import com.example.usecase.useCases.products.DeleteFromFavoriteUseCase
import com.example.usecase.useCases.products.GetProductByIdUseCase
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.utils.DetailsAssistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = DetailsAssistedFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val itemId: String,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addProductToFavoriteUseCase: AddProductToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
) : BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()),
    DetailsInteraction {

    init {
        getProductData()
    }

    private fun getProductData() {
        tryToExecute({ getProductByIdUseCase(itemId) }, ::onGetItemDetailsSuccess, {
            Log.d("details_exception", it.toString() + "...." + it.stackTrace.contentToString())
        })
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                getProductByIdUseCase(itemId).also { onGetItemDetailsSuccess(it) }
//            } catch (e: Exception) {
//                Log.d("details", e.message.toString())
//            }
//        }
    }

    private fun onGetItemDetailsSuccess(product: Product) {
        Log.d("details", product.toUiState().toString())
        updateState { product.toUiState() }
    }

    override fun onFavoriteClick(itemId: String, isFavorite: Boolean) {
        if (isFavorite)
            tryToExecute(
                { deleteFromFavoriteUseCase(itemId) },
                { onDeleteFavoriteSuccess() },
                {})
        else
            tryToExecute(
                { addProductToFavoriteUseCase(itemId) },
                ::onAddFavoriteSuccess,
                ::onAddFavoriteError
            )

    }

    fun updateTransitionData(image: String, name: String) {
        updateState { copy(imageUrl = image, title = name) }
    }

    private fun onAddFavoriteError(exception: Exception) {
        when(exception){
            is BadTokenException ->{
                sendUiEffect(DetailsUiEffect.NavigateToLogIn)
            }
        }
    }

    private fun onAddFavoriteSuccess(s: String) {
        updateState { copy(isFavorite = true) }
    }

    private fun onDeleteFavoriteSuccess() {
        updateState { copy(isFavorite = false) }
    }

    override fun addToCart(itemId: String) {

    }

    override fun onIncrementClick() {
        updateState {
            copy(quantityToOrder = quantityToOrder + 1)
        }
    }

    override fun onDecrementClick() {
        if (state.value.quantityToOrder > 1)
            updateState {
                copy(quantityToOrder = quantityToOrder - 1)
            }
    }

    override fun onAddToCartClick() {
        tryToExecute(
            { addProductToCartUseCase(itemId, state.value.quantityToOrder) },
            ::onAddToCartSuccess,
            ::onAddToCartError
        )
    }

    private fun onAddToCartError(exception: Exception) {

    }

    private fun onAddToCartSuccess(unit: Unit) {

    }
}