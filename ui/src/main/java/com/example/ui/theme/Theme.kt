package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = lightPrimary,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = PurpleGrey40,
    tertiary = Pink40
)


val lightCustomColor = CustomColors(
    primary = lightPrimary,
    textColor = lightTextColor,
    unFocusedTextField = lightUnFocusedTextField,
    hintColor = lightHintText,
    background = lightBackground,
    bottomNavColor = lightBottomNavColor,
    favoriteBackground = lightFavoriteBackground,
    onBackground87 = onLightBackground87,
    sizeUnselectedColor = lightSizeIconColor
)

private val ClozitHavenCustomColors = staticCompositionLocalOf { CustomColors() }

@Composable
fun GlobaStyleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicLightColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val systemUiController = rememberSystemUiController()

    if (darkTheme) {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    } else {
        systemUiController.setStatusBarColor(color = Color.White)
    }

    val customColors = if (darkTheme) lightCustomColor
    else lightCustomColor
    CompositionLocalProvider(
        ClozitHavenCustomColors provides customColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}

@Composable
fun MaterialTheme.CustomColors() = ClozitHavenCustomColors.current