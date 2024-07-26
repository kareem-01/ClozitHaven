package com.example.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
fun calculateSize(index: Int, sizes: List<Dp>): Dp {
    val rowSizeIndex = index % sizes.size
    val colSizeIndex = index / sizes.size
    return if (colSizeIndex % 2 == 0)
        sizes[rowSizeIndex]
    else
        sizes.reversed()[rowSizeIndex]
}