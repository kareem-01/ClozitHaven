package com.example.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.R
import com.example.viewmodel.signUp.TextType
import com.example.ui.theme.CustomColors
import com.example.ui.theme.Radius8
import com.example.ui.theme.Space2
import com.example.ui.theme.Space32

@Composable
fun AuthenticationField(
    onDoneClick: ((type: TextType, text: String) -> Unit)? = null,
    label: String,
    text: MutableState<String>,
    iconPainter: Painter,
    textType: TextType
) {
    val isFocused = remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    val interAction = remember { MutableInteractionSource() }
    OutlinedTextField(
        interactionSource = interAction,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { newFocus ->
                isFocused.value = newFocus.isFocused
                onDoneClick?.let { it(textType, text.value) }
            },
        shape = RoundedCornerShape(Radius8),
        label = { Text(text = label) },
        value = text.value,
        onValueChange = { text.value = it },
        leadingIcon = {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .width(Space32),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = if (isFocused.value) MaterialTheme.CustomColors().primary else MaterialTheme.CustomColors().hintColor
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(Space2)
                        .background(if (isFocused.value) MaterialTheme.CustomColors().primary else MaterialTheme.CustomColors().hintColor)
                )
            }
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onDoneClick?.let { it(textType, text.value) }
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = when (textType) {
                TextType.USERNAME -> KeyboardType.Text
                TextType.EMAIL -> KeyboardType.Email
                TextType.CONFIRMPASSWORD, TextType.PASSWORD -> KeyboardType.Password
                TextType.PHONE -> KeyboardType.Phone
            }
        ),
        visualTransformation = if (textType == TextType.PASSWORD || textType == TextType.CONFIRMPASSWORD) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun Preview() {
    AuthenticationField(
        label = "email",
        text = mutableStateOf(""),
        iconPainter = painterResource(id = R.drawable.profile_icon),
        textType = TextType.EMAIL,
    )
}