package com.example.ui.utils

import android.content.res.Resources.getSystem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

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
