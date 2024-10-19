package com.example.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.example.ui.theme.starColor

@Composable
fun StarRating(rating: String) {
    val colors = MaterialTheme.CustomColors()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.star_icon),
            contentDescription = null,
            tint = starColor

        )
        Text(
            text = rating,
            style = typography.bodyLarge,
            color = colors.hintColor
        )
    }
}