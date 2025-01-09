@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.screens.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ui.LocalNavController
import com.example.ui.R
import com.example.ui.composables.GlobaScaffold
import com.example.ui.composables.HomeCard
import com.example.ui.composables.HomeShimmer
import com.example.ui.nav.navigateToDetails
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Space16
import com.example.ui.theme.Space24
import com.example.ui.theme.Space4
import com.example.ui.utils.calculateSize
import com.example.viewmodel.home.HomeEffect
import com.example.viewmodel.home.HomeInteraction
import com.example.viewmodel.home.HomeUiState
import com.example.viewmodel.home.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),

    ) {
    val navController = LocalNavController.current
    val state by homeViewModel.state.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.message) {
        if (state.message != null) {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show().also {
                homeViewModel.clearMessage()
            }
        }
    }
    val tabPagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.categories.size + 1 }
    )

    HomeContent(
        homeViewModel,
        pagerState = pagerState,
        state,
        animatedVisibilityScope,
        tabPagerState = tabPagerState
    )

    LaunchedEffect(key1 = state.isLoading) {
        homeViewModel.effect.collectLatest {
            onEffect(it, navController)
        }
    }
}

fun onEffect(effect: HomeEffect, controller: NavController) {
    when (effect) {
        is HomeEffect.NavigateToDetails -> {
//            controller.navigateToDetails(effect.id)
        }

        else -> {}
    }


}

@Composable
private fun SharedTransitionScope.HomeContent(
    listener: HomeInteraction,
    pagerState: PagerState,
    state: HomeUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    tabPagerState: PagerState
) {
    val navController = LocalNavController.current
    val sizes = listOf(180.dp, 280.dp)
    val pagerImages = listOf(
        R.drawable.pager_image_1,
        R.drawable.pager_image_2,
        R.drawable.pager_image_3,
    )
    val coroutineScope = rememberCoroutineScope()
    val colors = MaterialTheme.CustomColors()

    LaunchedEffect(tabPagerState.targetPage) {
        if (tabPagerState.targetPage == tabPagerState.currentPage)
            return@LaunchedEffect
        listener.onTabClick(state.categories.getOrNull(tabPagerState.targetPage - 1)?.id)
    }

    GlobaScaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Space16)
        ) {
            Image(
                painter = painterResource(id = R.drawable.clozithavenlogo),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp),
                contentScale = ContentScale.Crop
            )

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 24.dp),

                ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    Image(
                        painter = painterResource(id = pagerImages[page]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(Radius12)),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Column {
                ScrollableTabRow(
                    selectedTabIndex = tabPagerState.targetPage,
                    edgePadding = Space16,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[tabPagerState.targetPage])
                                .padding(start = Space4, end = Space4),
                            color = colors.primary
                        )
                    },
                    divider = {},
                    containerColor = Color.Transparent
                ) {
                    Tab(
                        selected = tabPagerState.targetPage == 0,
                        onClick = {
                            listener.onTabClick(null)
                            coroutineScope.launch {
                                tabPagerState.animateScrollToPage(0)
                            }
                        }
                    ) {
                        Text(
                            text = "All",
                            modifier = Modifier.padding(
                                bottom = Space4,
                                start = 4.dp,
                                end = 4.dp
                            )
                        )
                    }
                    state.categories.forEachIndexed { index, category ->
                        Tab(
                            selected = tabPagerState.targetPage == index + 1,
                            onClick = {
                                listener.onTabClick(state.categories[index].id)
                                coroutineScope.launch {
                                    tabPagerState.animateScrollToPage(index + 1)
                                }
                            },
                            selectedContentColor = colors.primary,
                            unselectedContentColor = colors.textColor,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Text(
                                text = category.name,
                                modifier = Modifier.padding(
                                    bottom = Space4,
                                    start = 4.dp,
                                    end = 4.dp
                                )
                            )
                        }

                    }
                }
            }
            HorizontalPager(state = tabPagerState) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(-Space24),
                    verticalItemSpacing = Space16,
                ) {
                    if (state.items.isEmpty() && state.isLoading.not())
                        item(span = StaggeredGridItemSpan.FullLine) {
                            Text(
                                text = "No items found",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                color = colors.textColor,
                                style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
                                textAlign = TextAlign.Center
                            )
                        }
                    else
                        items(if (state.items.isEmpty()) 4 else state.items.size) { index ->
                            val imageSize = calculateSize(index = index, sizes = sizes)
                            HomeShimmer(isLoading = state.isLoading) {
                                HomeCard(
                                    item = state.items[index],
                                    onItemClick = {
                                        state.items[index].apply {
                                            navController.navigateToDetails(itemId, image, itemName)
                                        }
                                    },
                                    size = imageSize,
                                    onFavoriteClick = listener::onFavoriteClick,
                                    visibilityScope = animatedVisibilityScope,
                                )
                            }
                        }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {

}