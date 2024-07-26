package com.example.ui.screens.home

import android.widget.Toast
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.R
import com.example.ui.composables.GlobaScaffold
import com.example.ui.composables.HomeCard
import com.example.ui.composables.HomeShimmer
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Space16
import com.example.ui.theme.Space24
import com.example.ui.theme.Space4
import com.example.ui.utils.calculateSize
import com.example.viewmodel.home.HomeInteraction
import com.example.viewmodel.home.HomeUiState
import com.example.viewmodel.home.HomeViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    val border = remember {
        mutableFloatStateOf(0f)
    }
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
    HomeContent(border, homeViewModel, pagerState = pagerState, state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeContent(
    sliderValue: MutableState<Float>,
    listener: HomeInteraction,
    pagerState: PagerState,
    state: HomeUiState,
) {
    val sizes = listOf(230.dp, 270.dp)
    val pagerImages = listOf(
        R.drawable.pager_image_1,
        R.drawable.pager_image_2,
        R.drawable.pager_image_3,
    )
    val selectedTab = remember { mutableIntStateOf(0) }
    val colors = MaterialTheme.CustomColors()
    GlobaScaffold {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(-Space24),
            verticalArrangement = Arrangement.spacedBy(Space16),

            ) {
            item() {
                Image(
                    painter = painterResource(id = R.drawable.clozithavenlogo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    contentScale = ContentScale.Crop
                )

            }
            item(span = { GridItemSpan(2) }) {
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
            }
            item(span = { GridItemSpan(2) }) {
                ScrollableTabRow(
                    modifier = Modifier.padding(),
                    selectedTabIndex = selectedTab.value,
                    edgePadding = Space16,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[selectedTab.value])
                                .padding(start = Space4, end = Space4),
                            color = colors.primary
                        )
                    },
                    divider = {},
                    containerColor = Color.Transparent
                ) {
                    Tab(
                        selected = selectedTab.value == 0,
                        onClick = {
                            selectedTab.value = 0
                            listener.onTabClick(null)

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
                            selected = selectedTab.value == index + 1,
                            onClick = {
                                selectedTab.value = index + 1
                                listener.onTabClick(state.categories[index].id)
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
            items(state.items.size) { index ->
                val imageSize = calculateSize(index = index, sizes = sizes)
                HomeShimmer(isLoading = state.isLoading) {
                    HomeCard(
                        item = state.items[index],
                        onItemClick = listener::onItemClick,
                        size = imageSize,
                        onFavoriteClick = listener::onFavoriteClick
                    )
                }
            }

        }

//    Column {
//        val density = LocalDensity.current.density
//        Image(
//            modifier = Modifier
//                .size(200.dp)
//                .onGloballyPositioned { cordinates ->
//                    Log.i("HMM", (cordinates.size.width / density).dp.toString())
//                },
//            painter = painterResource(id = R.drawable.sign_up_image),
//            contentDescription = null
//        )
//        val size = remember {
//            mutableStateOf(0.dp)
//        }
//        Box(
//            modifier = Modifier
//                .width(360.dp)
//                .height(100.dp)
//                .border((200 * sliderValue.value).dp, color = Color.Black)
//                .background(Color.Red)
//                .onGloballyPositioned { cordinates ->
//                    Log.i("Box", (cordinates.size.width / density).toString())
//                    Log.i("density", (density).toString())
//                    size.value = (cordinates.size.width / density).dp
//                }
//        )
//
//        Box(
//            modifier = Modifier
//                .size(size.value)
//                .background(Color.Black)
//        )
//        Slider(value = sliderValue.value, onValueChange = { value ->
//            sliderValue.value = value
//        })
//    }


    }
}

@Preview
@Composable
private fun Preview() {

}