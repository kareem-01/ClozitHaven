package com.example.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ui.theme.GlobaStyleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GlobaStyleTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    (LocalView.current.context as Activity).window.statusBarColor =
                        Color.White.toArgb()
                    var otpText by remember { mutableStateOf("") }
                    val focusRequester = remember { FocusRequester() }
                    val keyboardController = LocalSoftwareKeyboardController.current

                    GlobaStyleApp()
//                    LaunchedEffect(Unit) {
//                        focusRequester.requestFocus()
//                        keyboardController?.show()
//                    }
//                    OtpInputField(
//                        modifier = Modifier
//                            .padding(top = 16.dp)
//                            .focusRequester(focusRequester),
//                        otpText = otpText,
//                        onOtpModified = { newText, isFilled ->
//                            otpText = newText
//                            if (isFilled) {
//                                keyboardController?.hide()
//                            }
//                        }
//                    )
                }
            }
        }
    }
}