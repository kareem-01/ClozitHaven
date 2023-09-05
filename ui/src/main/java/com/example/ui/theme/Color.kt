package com.example.ui.theme

import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// light
val lightPrimary = Color(0xFF3894A1)
val lightTextColor = Color(0xFF06004F)
val lightUnFocusedTextField = Color(0xFFCCCFC7)
val lightHintText = Color(0xFF9A9A9A)
val lightBackground = Color(0xFFFFFFFF)
// end light

//Dark

//end Dark


@Immutable
data class CustomColors(
    val primary: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val unFocusedTextField: Color = Color.Unspecified,
    val hintColor: Color = Color.Unspecified,
    val background:Color = Color.Unspecified
)