@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.ui.theme.CustomColors
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
    var loading = true
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .noRippleClick { onItemClick(item.itemId) }
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(Space8)
        ) {
            Image(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${item.itemId}"),
                        animatedVisibilityScope = visibilityScope
                    )
                    .clip(RoundedCornerShape(Radius8))
                    .height(size)
                    .fillMaxWidth()
                    .shimmerEffect(loading),
                painter = rememberAsyncImagePainter(model = item.image, onSuccess = {
                    loading = false
                }),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
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
            Row {
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
    SharedTransitionLayout {


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
}