package com.example.ui.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.R
import com.example.ui.composables.HomeCard
import com.example.ui.composables.HomeShimmer
import com.example.ui.screens.search.components.RecentSearchItem
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Space16
import com.example.ui.theme.Space8
import com.example.ui.utils.calculateSize
import com.example.ui.utils.noRippleClick
import com.example.viewmodel.search.SearchInteraction
import com.example.viewmodel.search.SearchUiState
import com.example.viewmodel.search.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    ScreenContent(state, viewModel)
}

@Composable
private fun ScreenContent(state: SearchUiState, listener: SearchInteraction) {
    val colors = MaterialTheme.CustomColors()
    val sizes = listOf(230.dp, 270.dp)
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(Space8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                interactionSource = interactionSource,
                value = state.searchQuery, onValueChange = listener::onQueryChanged,
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty())
                        Icon(
                            painter = painterResource(id = R.drawable.exit),
                            contentDescription = null,
                            modifier = Modifier.noRippleClick {
                                listener.clearSearchQuery()
                            }
                        )
                },
                placeholder = {
                    Row(horizontalArrangement = Arrangement.spacedBy(Space8)) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            tint = colors.unfocusedColor
                        )
                        Text(text = "Search for anything", color = colors.unfocusedColor)
                    }
                },
                shape = RoundedCornerShape(Space8),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(Radius12))
                    .border(
                        1.dp,
                        color = if (focused) Color.Black else Color.LightGray,
                        RoundedCornerShape(Radius12)
                    )
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Space8))
                    .background(colors.primary, RoundedCornerShape(Space8))
                    .clickable { listener.onFilterClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    modifier = Modifier.padding(Radius12)
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.searchQuery.isNotEmpty() && state.searchResults.isNotEmpty()) {
                LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
                    items(state.searchResults.size) { index ->
                        val imageSize = calculateSize(index = index, sizes = sizes)

                        HomeShimmer(isLoading = state.isLoading) {
                            HomeCard(
                                item = state.searchResults[index],
                                onItemClick = listener::onItemClick,
                                size = imageSize,
                                onFavoriteClick = listener::onFavoriteClick
                            )
                        }
                    }
                }
            } else if (state.searchQuery.isEmpty() && state.recentlySearched.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Space16)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.no_recent_searches),
                        contentDescription = null,
                        tint = colors.textColor
                    )
                    Text(text = "there are no recently searched items", color = colors.textColor)

                }
            } else if (state.searchQuery.isEmpty() && state.recentlySearched.isNotEmpty()) {
                Column(modifier = Modifier) {
                    Row {

                    }
                    LazyColumn {
                        items(state.recentlySearched.size) { index ->
                            RecentSearchItem(item = state.recentlySearched[index])
                        }
                    }
                }
            } else {
                this@Column.AnimatedVisibility(
                    visible = state.searchQuery.isNotEmpty() && state.searchResults.isEmpty(),
                    modifier = Modifier.align(Alignment.Center),
                    enter = fadeIn(tween(2000)) + expandVertically(tween(2000))
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Space16)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.no_search_results),
                            contentDescription = null,
                            tint = colors.textColor
                        )
                        Text(
                            text = "Try searching for a different combination of filters and keywords.",
                            color = colors.textColor,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }

}