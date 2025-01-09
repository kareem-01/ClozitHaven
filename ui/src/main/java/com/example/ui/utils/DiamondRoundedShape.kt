package com.example.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class DiamondRoundedShape(
    private val radius: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radiusPx = with(density) {
            radius.toPx()
        }
        val halfRadius = radiusPx / 2
        val smoothWidth = .4f * halfRadius
        return Outline.Generic(
            path = Path().apply {
                reset()
                moveTo(size.width / 2 - halfRadius, halfRadius)
                arcTo(
                    rect = Rect(
                        offset = Offset(x = size.width / 2 - halfRadius, y = 0f),
                        size = Size(width = radiusPx, height = radiusPx)
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
                lineTo(
                    size.width - halfRadius + smoothWidth,
                    size.height / 2 - halfRadius + .1f * halfRadius
                )
                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = size.width - radiusPx,
                            y = size.height / 2 - halfRadius
                        ),
                        size = Size(width = radiusPx, height = radiusPx)
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
                lineTo(size.width / 2 + halfRadius, size.height - halfRadius)
                arcTo(
                    rect = Rect(
                        offset = Offset(
                            x = size.width / 2 - halfRadius,
                            y = size.height - radiusPx
                        ),
                        size = Size(width = radiusPx, height = radiusPx)
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
                lineTo(halfRadius, size.height / 2 + halfRadius)
                arcTo(
                    rect = Rect(
                        offset = Offset(x = 0f, y = size.height / 2 - halfRadius),
                        size = Size(width = radiusPx, height = radiusPx)
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 180f,
                    forceMoveTo = false,
                )
                close()
            }
        )
    }
}

@Preview
@Composable
private fun DiamondPreview() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                color = Color.Red,
                shape = DiamondRoundedShape(radius = 16.dp)
            )
    )

}