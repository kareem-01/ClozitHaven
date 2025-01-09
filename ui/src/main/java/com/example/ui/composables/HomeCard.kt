@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Radius8
import com.example.ui.theme.Space8
import com.example.ui.utils.noRippleClick
import com.example.viewmodel.home.ItemCard

@Composable
fun SharedTransitionScope.HomeCard(
    item: ItemCard,
    onItemClick: (id: String) -> Unit,
    size: Dp = 200.dp,
    visibilityScope: AnimatedVisibilityScope,
    onFavoriteClick: (itemId: String, isFavorite: Boolean) -> Unit,
) {
    val typography = MaterialTheme.typography
    val colors = MaterialTheme.CustomColors()
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .noRippleClick { onItemClick(item.itemId) }
    ) {
        Column(
            modifier = Modifier.padding(bottom = Space8),
            verticalArrangement = Arrangement.spacedBy(Space8),
        ) {
            AsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${item.itemId}"),
                        animatedVisibilityScope = visibilityScope
                    )
                    .clip(RoundedCornerShape(Radius12))
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = item.image,
                imageLoader = LocalContext.current.imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space8),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.itemName,
                    style = typography.bodyLarge,
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "title-${item.itemId}"),
                            animatedVisibilityScope = visibilityScope
                        )
                        .fillMaxWidth(0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                StarRating(rating = item.rating)
            }
            Row(modifier = Modifier.padding(horizontal = Space8)) {
//                AnimatedVisibility(visible = item.priceBeforeDiscount != null) {
//                    Box {
//                        Text(
//                            text = item.priceBeforeDiscount!!,
//                            style = typography.bodyLarge,
//                            color = colors.hintColor
//                        )
//                        Spacer(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(Space2)
//                                .background(Color.Black)
//                                .align(Alignment.Center)
//                        )
//                    }
//
//                }
                Text(
                    text = item.currentPrice,
                    style = typography.bodyLarge,
                    color = colors.hintColor
                )
            }
        }
        FavoriteIcon(
            itemId = item.itemId,
            isFavorite = item.isFavorite,
            onFavoriteClick = onFavoriteClick,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(
                shape = RoundedCornerShape(Radius8),
                color = Color.Yellow
            )
            .border(
                width = 5.dp,
                color = Color.Red
            )
            .graphicsLayer {
                rotationY = 90f
                shape = RoundedCornerShape(Radius8)
            }
    )


//    SharedTransitionLayout {
//        AnimatedVisibility(true) {
//            HomeCard(
//                item = ItemCard(
//                    isFavorite = false,
//                    itemId = "",
//                    itemName = "HANA",
//                    currentPrice = "300",
//                    rating = "4.5",
//                    image = "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680401528923-1.jpeg",
//                ),
//                {},
//                50.dp,
//                visibilityScope = this,
//            ) { string, boolean ->
//
//            }
//        }
//    }

//        HomeCard(
//            item = ItemCard(
//                isFavorite = false,
//                itemId = "",
//                itemName = "HANA",
//                currentPrice = "300",
//                rating = "4.5",
//                image = "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680401528923-1.jpeg",
//            ),
//            {},
//            50.dp,
//            visibilityScope = this,
//        ) { string, boolean ->
//
//        }
}
