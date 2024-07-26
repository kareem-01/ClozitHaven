package com.example.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius8
import com.example.ui.theme.Space4
import com.example.ui.theme.Space8
import com.example.ui.theme.starColor
import com.example.ui.utils.noRippleClick
import com.example.viewmodel.home.ItemCard

@Composable
fun HomeCard(
    item: ItemCard,
    onItemClick: (id: String) -> Unit,
    size: Dp = 200.dp,
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
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(Space8)
        ) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(Radius8))
                    .height(size)
                    .fillMaxWidth()
                    .shimmerEffect(),
                painter = rememberAsyncImagePainter(model = item.image),
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
                    modifier = Modifier.fillMaxWidth(0.6f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_icon),
                        contentDescription = null,
                        tint = starColor

                    )
                    Text(
                        text = item.rating,
                        style = typography.bodyLarge,
                        color = colors.hintColor
                    )
                }
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
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(Space8)
                .clip(CircleShape)
                .background(colors.favoriteBackground, CircleShape)
//                .offset(x = (-10).dp, y = 5.dp)
                .noRippleClick { onFavoriteClick(item.itemId, item.isFavorite) },
            contentAlignment = Alignment.Center
        ) {
            if (item.isFavorite)
                Icon(
                    modifier = Modifier.padding(Space4),
                    painter = painterResource(id = R.drawable.filled_heart_icon),
                    contentDescription = null,
                    tint = colors.primary
                )
            else
                Icon(
                    modifier = Modifier.padding(Space4),
                    painter = painterResource(id = R.drawable.heart_icon),
                    contentDescription = null,
                )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeCard(
        item = ItemCard(
            isFavorite = false,
            itemId = "",
            itemName = "HANA",
            currentPrice = "300",
            rating = "4.5",
            image = "https://res.cloudinary.com/dwp0imlbj/image/upload/Route-Academy-products/1680401528923-1.jpeg",
        ),
        {},
        50.dp,
    ) { string, boolean ->

    }
}