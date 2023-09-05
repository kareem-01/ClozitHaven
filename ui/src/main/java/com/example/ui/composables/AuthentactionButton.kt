package com.example.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius12
import com.example.ui.theme.Space16

@Composable
fun AuthinticationButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.CustomColors().primary, shape = RoundedCornerShape(Radius12))
            .clip(RoundedCornerShape(Radius12))
            .clickable {

                onClick()
            }
    ) {
        Text(
            text = text, modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = Space16),
            color = Color.White
        )
    }
}
