package com.example.ui.screens.details

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ui.LocalNavController
import com.example.ui.R
import com.example.ui.composables.FavoriteIcon
import com.example.ui.composables.StarRating
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Radius24
import com.example.ui.theme.Radius4
import com.example.ui.theme.Space12
import com.example.ui.theme.Space16
import com.example.ui.theme.Space24
import com.example.ui.theme.Space4
import com.example.ui.theme.Space8
import com.example.ui.theme.minusIconColor
import com.example.ui.utils.colorModifier
import com.example.ui.utils.colors
import com.example.ui.utils.noRippleClick
import com.example.ui.utils.productDetailBoundsTransform
import com.example.ui.utils.sizes
import com.example.viewmodel.details.DetailsInteraction
import com.example.viewmodel.details.DetailsUiState
import com.example.viewmodel.details.DetailsViewModel
import com.example.viewmodel.utils.DetailsAssistedFactory

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    productId: String,
    image: String,
    name: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: DetailsViewModel = hiltViewModel(
        creationCallback = { factory: DetailsAssistedFactory ->
            factory.create(itemId = productId)
        }
    )
) {
    viewModel.updateTransitionData(image, name)
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    DetailsContent(state, viewModel, animatedVisibilityScope)
    state.message?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.DetailsContent(
    state: DetailsUiState,
    listener: DetailsInteraction,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.CustomColors().background)
            .systemBarsPadding()
    ) {
        AsyncImage(
            model = state.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "image-${state.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = productDetailBoundsTransform,
//                    exit = fadeOut(nonSpatialExpressiveSpring()),
//                    enter = fadeIn(nonSpatialExpressiveSpring()),
                )
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Space16),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Space8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow__left_icon),
                    contentDescription = null,
                    modifier = Modifier.noRippleClick {

                    }
                )
                Text(text = "Details", color = MaterialTheme.CustomColors().textColor)
            }
            FavoriteIcon(
                itemId = state.id,
                isFavorite = state.isFavorite,
                onFavoriteClick = listener::onFavoriteClick
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .align(Alignment.BottomCenter)
                .background(
                    MaterialTheme.CustomColors().background,
                    RoundedCornerShape(topStart = Radius24, topEnd = Radius24)
                )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = Space16, vertical = Space24),
                verticalArrangement = Arrangement.spacedBy(Space16)
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(Space8)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = state.title,
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = "title-${state.id}"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        )
                        StarRating(rating = state.rating)
                    }
                    Text(
                        text = if (state.remainingQuantity > 0) "Available in Stock" else "Out of Stock",
                        modifier = Modifier.alpha(.6f)
                    )

                }

                Text(text = state.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                if (state.isFashion)
                    Column(verticalArrangement = Arrangement.spacedBy(Space8)) {
                        Text(text = "Size")
                        Row {
                            repeat(3) { index ->
                                val isSelected = state.selectedSize == index
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.size_icon),
                                        contentDescription = null,
                                        tint = if (isSelected) MaterialTheme.CustomColors().primary else MaterialTheme.CustomColors().sizeUnselectedColor
                                    )
                                    Text(
                                        text = sizes[index]!!,
                                        color = if (isSelected) Color.White else MaterialTheme.CustomColors().textColor
                                    )
                                }
                            }
                        }
                    }
                Column(verticalArrangement = Arrangement.spacedBy(Space8)) {
                    Text(text = "Color")
                    Row(horizontalArrangement = Arrangement.spacedBy(Space8)) {
                        repeat(5) { index ->
                            val isSelected = state.selectedColor == index
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .colorModifier(
                                        isSelected = isSelected,
                                        color = colors[index]!!
                                    )
                            )
                        }
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(text = "Quantity")
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                Space4
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.CustomColors().textColor,
                                modifier = Modifier
                                    .background(
                                        Color(0xFFF8F8FA),
                                        RoundedCornerShape(Radius4),
                                    )
                                    .clickable { listener.onIncrementClick() }
                            )
                            Text(
                                text = state.quantityToOrder.toString(),
                                color = MaterialTheme.CustomColors().textColor
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.minus_icon),
                                contentDescription = null,
                                tint = if (state.quantityToOrder == 1) minusIconColor else Color.Black,
                                modifier = Modifier
                                    .background(
                                        Color(0xFFF8F8FA),
                                        RoundedCornerShape(Radius4),
                                    )
                                    .clickable { listener.onDecrementClick() }
                            )
                        }
                    }

                    Button(
                        onClick = { listener.onAddToCartClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.CustomColors().primary),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = Space12),
                        shape = RoundedCornerShape(Radius12)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(Space8),
                        ) {
                            Text(
                                text = (state.price * state.quantityToOrder).toString() + " EGP",
                                color = Color.White
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.bag_icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
