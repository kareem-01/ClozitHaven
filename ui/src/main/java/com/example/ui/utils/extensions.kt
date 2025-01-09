package com.example.ui.utils

import android.content.res.Resources.getSystem
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius16

@Stable
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    val interaction = MutableInteractionSource()
    return this.then(
        Modifier.clickable(
            interactionSource = interaction,
            indication = null,
            onClick = onClick
        )
    )
}

val Dp.float: Float get() = this.value * getSystem().displayMetrics.density

@Composable
fun Modifier.colorModifier(color: Color, isSelected: Boolean = false): Modifier {
    return this
        .graphicsLayer {
            rotationZ = 45f
        }
        .background(
            shape = RoundedCornerShape(Radius16),
            color = color
        )
        .border(
            width = 2.dp,
            color = if (isSelected) MaterialTheme.CustomColors().primary else Color.Transparent,
            shape = RoundedCornerShape(Radius16)
        )

}