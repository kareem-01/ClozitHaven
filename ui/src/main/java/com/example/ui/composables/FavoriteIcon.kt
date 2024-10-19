package com.example.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Space4
import com.example.ui.theme.Space8
import com.example.ui.utils.noRippleClick

@Composable
fun FavoriteIcon(
    itemId: String,
    isFavorite: Boolean,
    onFavoriteClick: (itemId: String, isFavorite: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.CustomColors()
    Box(
        modifier = modifier
            .padding(Space8)
            .clip(CircleShape)
            .background(colors.favoriteBackground, CircleShape)
//                .offset(x = (-10).dp, y = 5.dp)
            .noRippleClick { onFavoriteClick(itemId, isFavorite) },
        contentAlignment = Alignment.Center
    ) {
        if (isFavorite)
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