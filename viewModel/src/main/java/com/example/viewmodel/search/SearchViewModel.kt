package com.example.viewmodel.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.entity.products.Product
import com.example.usecase.UseCases.products.GetProductsUseCase
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.home.toItemCard
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
class SearchViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : BaseViewModel<SearchUiState, SearchUiEffect>(SearchUiState()),
    SearchInteraction {

    private val queryChannel = Channel<String>(Channel.CONFLATED)

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            queryChannel
                .consumeAsFlow()
                .debounce(900)
                .collectLatest { query ->
                    if (query.isNotEmpty()) {
                        updateState { copy(isLoading = true) }
                        Log.d("SearchViewModel", "Query: $query")
                        search(query)
                    }
                }
            tryToExecute(
                suspend {
                    getProductsUseCase()
                }, ::onGetProductsSuccess, ::onGetProductsFail
            )
        }
    }

    private fun onGetProductsFail(exception: Exception) {
        updateState { copy(isLoading = false, isSuccess = false) }
        Log.e("SearchViewModelError", "onGetProductsFail: ${exception.message}")
    }

    private fun onGetProductsSuccess(products: List<Product>) {
        updateState { copy(isLoading = false, allItems = products.toItemCard(), isSuccess = true) }
    }

    override fun search(query: String) {
        val filteredItems = state.value.allItems.filter {
            it.itemName.contains(query, ignoreCase = true)
        }
        updateState { copy(searchResults = filteredItems) }
    }

    override fun onItemClick(id: String) {
        TODO("Not yet implemented")
    }

    override fun onQueryChanged(query: String) {
        updateState {
            copy(searchQuery = query)
        }
        queryChannel.trySend(query)

        // 2nd way to handle search
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            // search
        }
    }

    override fun onFilterClick() {
        TODO("Not yet implemented")
    }

    override fun onFavoriteClick(itemId: String, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clearSearchQuery() {
        updateState {
            copy(searchQuery = "")
        }
    }

}