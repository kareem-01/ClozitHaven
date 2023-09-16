package com.example.ui.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.viewmodel.BaseViewModel
import com.example.viewmodel.utils.throttleFirst
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CollectUiEffect(
    effect: SharedFlow<BaseViewModel.UiEffect>,
    effectHandler: (effect: BaseViewModel.UiEffect) -> Unit
) {
    val throttledEffect = effect.throttleFirst(1000)
    LaunchedEffect(key1 = Unit) {
        throttledEffect.collectLatest { effect ->
            effectHandler(effect)
        }
    }
}