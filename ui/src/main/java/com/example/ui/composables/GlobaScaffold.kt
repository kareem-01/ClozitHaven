package com.example.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.ui.LocalNavController
import com.example.ui.R
import com.example.ui.theme.CustomColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobaScaffold(
    hasAppBar: Boolean = false,
    hasBackArrow: Boolean = false,
    title: String = "",
    titleColor: Color = MaterialTheme.CustomColors().onBackground87,
    hasHeart: Boolean = false,
    isFavorite: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val navController = LocalNavController.current
    val colors = MaterialTheme.CustomColors()
    if (isSystemInDarkTheme()) {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    } else {
        systemUiController.setStatusBarColor(color = Color.White)
    }
    val signUpTopBar = TopAppBar(title = { /*TODO*/ })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(visible = hasAppBar) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = title,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleMedium,
                                color = titleColor,
                                overflow = TextOverflow.Ellipsis
                            )
                            AnimatedVisibility(visible = hasHeart) {
                                Icon(
                                    painter = painterResource(id = if (isFavorite) R.drawable.filled_heart_icon else R.drawable.heart_icon),
                                    contentDescription = null,
                                    tint = if (isFavorite) MaterialTheme.CustomColors().primary else MaterialTheme.CustomColors().textColor
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        AnimatedVisibility(visible = hasBackArrow) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow__left_icon),
                                    tint = colors.textColor,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        },
        containerColor = MaterialTheme.CustomColors().background
        ) { paddingValues ->

        content(paddingValues)
    }
}