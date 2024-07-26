package com.example.ui.screens.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.example.viewmodel.home.ItemCard

@Composable
fun RecentSearchItem(item: ItemCard) {
    Row {
        AsyncImage(model = item.image, contentDescription = null)
        Column {
            Text(text = item.itemName, color = MaterialTheme.CustomColors().textColor)
            Text(text = item.currentPrice, modifier = Modifier.alpha(.7f))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.star_icon),
                    contentDescription = null
                )
                Text(text = item.rating, modifier = Modifier.alpha(.7f))
            }
        }
    }
}