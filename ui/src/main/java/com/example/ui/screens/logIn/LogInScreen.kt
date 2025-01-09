package com.example.ui.screens.logIn

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.LocalNavController
import com.example.ui.R
import com.example.ui.composables.AuthenticationField
import com.example.ui.composables.AuthinticationButton
import com.example.ui.composables.SnackBar
import com.example.ui.composables.keyboardAsState
import com.example.ui.nav.navigateToMainNavGraph
import com.example.ui.nav.navigateToSignUp
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius24
import com.example.ui.theme.Space16
import com.example.ui.theme.Space24
import com.example.ui.theme.Space8
import com.example.ui.utils.CollectUiEffect
import com.example.ui.utils.noRippleClick
import com.example.viewmodel.LogIn.LogInEffect
import com.example.viewmodel.LogIn.LogInInteraction
import com.example.viewmodel.LogIn.LogInUiState
import com.example.viewmodel.LogIn.LogInViewModel
import com.example.viewmodel.signUp.TextType

@Composable
fun LogInScreen(
    viewModel: LogInViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    CollectUiEffect(effect = viewModel.effect) { effect ->
        when (effect) {
            LogInEffect.NavigateToLogIn -> navController.navigateToSignUp()
            LogInEffect.NavigateBack -> navController.popBackStack()
            LogInEffect.NavigateToHome -> navController.navigateToMainNavGraph()
        }
    }

    LogInContent(email, password, viewModel, state)
}

@Composable
fun LogInContent(
    email: MutableState<String>,
    password: MutableState<String>,
    interaction: LogInInteraction,
    state: LogInUiState
) {
    val colors = MaterialTheme.CustomColors()
    val typography = MaterialTheme.typography
    val focusManager = LocalFocusManager.current
    val isKeyboardVisible = keyboardAsState()

    LaunchedEffect(isKeyboardVisible.value) {
        if (!isKeyboardVisible.value) {
            focusManager.clearFocus()
            interaction.updateAllStates(
                email = email.value,
                password = password.value
            )
        }
    }
    LaunchedEffect(key1 = state.email, key2 = state.password) {
        Log.i("HMM", state.toString())
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {
        Image(
            painter = painterResource(id = R.drawable.log_in_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.37f),
            contentScale = ContentScale.FillBounds,
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.66f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = Radius24, topEnd = Radius24),
            colors = CardDefaults.cardColors(containerColor = colors.background)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = Space24, start = Space16, end = Space16),
                verticalArrangement = Arrangement.spacedBy(Space16)
            ) {
                Text(
                    text = stringResource(id = R.string.Login),
                    style = typography.titleLarge,
                    color = colors.textColor
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(Space16)
                ) {
                    AuthenticationField(
                        onDoneClick = interaction::updateUiState,
                        label = stringResource(id = R.string.email),
                        text = email,
                        iconPainter = painterResource(id = R.drawable.email_icon),
                        textType = TextType.EMAIL,
                    )
                    AuthenticationField(
                        onDoneClick = interaction::updateUiState,
                        label = stringResource(id = R.string.password),
                        text = password,
                        iconPainter = painterResource(id = R.drawable.lock_icon),
                        textType = TextType.PASSWORD,
                    )
                    AuthinticationButton(
                        text = stringResource(id = R.string.Login),
                        onClick = interaction::logIn
                    )
                }

                val signUp = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colors.hintColor)) {
                        append(stringResource(id = R.string.dontHaveAccount))
                    }
                    withStyle(style = SpanStyle(color = colors.primary)) {
                        append(stringResource(id = R.string.signUp))
                    }
                }
                Text(
                    text = signUp,
                    modifier = Modifier
                        .padding(top = Space8)
                        .noRippleClick { interaction.onLogInClick() })
            }
        }
        state.message?.let {
            SnackBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = it,
                onClear = interaction::clearMessage
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    LogInScreen()
}