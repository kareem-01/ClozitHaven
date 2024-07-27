package com.example.ui.screens.signUp

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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.ui.nav.navigateToLogIn
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius24
import com.example.ui.theme.Space16
import com.example.ui.theme.Space8
import com.example.ui.utils.CollectUiEffect
import com.example.ui.utils.noRippleClick
import com.example.viewmodel.signUp.SignUpEffect
import com.example.viewmodel.signUp.SignUpInteraction
import com.example.viewmodel.signUp.SignUpUiState
import com.example.viewmodel.signUp.SignUpViewModel
import com.example.viewmodel.signUp.TextType

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = hiltViewModel()) {

    val navController = LocalNavController.current
    val state by signUpViewModel.state.collectAsState()
    val userNameText = rememberSaveable {
        mutableStateOf("")
    }
    val emailText = rememberSaveable {
        mutableStateOf("")
    }
    val passwordText = rememberSaveable {
        mutableStateOf("")
    }
    val confirmPasswordText = rememberSaveable {
        mutableStateOf("")
    }
    val phoneText = rememberSaveable {
        mutableStateOf("")
    }

    CollectUiEffect(effect = signUpViewModel.effect) { effect ->
        when (effect) {
            SignUpEffect.NavigateToLogIn -> navController.navigateToLogIn()
            SignUpEffect.NavigateBack -> navController.popBackStack()
        }
    }
    SignUpContent(
        state = state,
        interaction = signUpViewModel,
        userName = userNameText,
        email = emailText,
        password = passwordText,
        confirmPassword = confirmPasswordText,
        phone = phoneText,
    )
}


@Composable
private fun SignUpContent(
    state: SignUpUiState,
    interaction: SignUpInteraction,
    userName: MutableState<String>,
    email: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>,
    phone: MutableState<String>
) {
    val typography = MaterialTheme.typography
    val colors = MaterialTheme.CustomColors()
    val focusManager = LocalFocusManager.current
    val isKeyboardVisible = keyboardAsState()

    LaunchedEffect(isKeyboardVisible.value) {
        if (!isKeyboardVisible.value) {
            focusManager.clearFocus()
            interaction.updateAllStates(
                userName = userName.value,
                email = email.value,
                password = password.value,
                rePassword = confirmPassword.value,
                phoneNumber = phone.value
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.sign_up_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.37f),
            contentScale = ContentScale.FillBounds
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.69f)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = Radius24, topEnd = Radius24),
            colors = CardDefaults.cardColors(containerColor = colors.background)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Space8),
                verticalArrangement = Arrangement.spacedBy(Space16)
            ) {
                Text(
                    text = stringResource(id = R.string.signUp),
                    style = typography.titleLarge,
                    color = colors.textColor
                )
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = Space16),
                    verticalArrangement = Arrangement.spacedBy(Space16),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.userName),
                            text = userName,
                            iconPainter = painterResource(id = R.drawable.profile_icon),
                            textType = TextType.USERNAME,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.email),
                            text = email,
                            iconPainter = painterResource(id = R.drawable.email_icon),
                            textType = TextType.EMAIL,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.password),
                            text = password,
                            iconPainter = painterResource(id = R.drawable.lock_icon),
                            textType = TextType.PASSWORD,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.confirmPassword),
                            text = confirmPassword,
                            iconPainter = painterResource(id = R.drawable.lock_icon),
                            textType = TextType.CONFIRMPASSWORD,
                        )
                    }
                    item {
                        AuthenticationField(
                            onDoneClick = interaction::updateUiState,
                            label = stringResource(id = R.string.phoneNumber),
                            text = phone,
                            iconPainter = painterResource(id = R.drawable.phone_icon),
                            textType = TextType.PHONE,
                        )
                    }
                    item {
                        AuthinticationButton(
                            text = stringResource(id = R.string.signUp),
                            onClick = interaction::signUp
                        )
                    }
                    item {
                        val logInText = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = colors.hintColor)) {
                                append(stringResource(id = R.string.haveAccount))
                            }
                            withStyle(style = SpanStyle(color = colors.primary)) {
                                append(stringResource(id = R.string.Login))
                            }
                        }

                        Text(
                            text = logInText,
                            modifier = Modifier
                                .padding(top = Space8)
                                .noRippleClick { interaction.onLogInClick() })
                    }
                }
            }


        }
        state.message?.let {
            SnackBar(modifier = Modifier.align(Alignment.BottomCenter), text = it)
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SignUpScreen()
}
