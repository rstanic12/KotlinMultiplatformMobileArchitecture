package com.kotlin.multiplatform.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.kotlin.multiplatform.core.presentation.navigation.PreviewRootComponent
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent.Child.HomeChild
import com.kotlin.multiplatform.core.presentation.navigation.RootComponent.Child.LoginChild
import com.kotlin.multiplatform.features.home.HomeLayout
import com.kotlin.multiplatform.features.login.InternetBannerLayout
import com.kotlin.multiplatform.features.login.LoginLayout

@Composable
fun RootLayout(component: RootComponent, modifier: Modifier = Modifier) {
    val slot by component.slot.subscribeAsState()

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Box {
                Children(
                    stack = component.stack,
                    modifier = modifier,
                    animation = stackAnimation(fade() + scale())
                ) {
                    when (val child = it.instance) {
                        is LoginChild -> LoginLayout(child.component)
                        is HomeChild -> HomeLayout(child.component)
                    }
                }
                when (val child = slot.child?.instance) {
                    is RootComponent.SlotChild.InternetBannerChild -> InternetBannerLayout(child.component)
                    null -> {}
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    RootLayout(PreviewRootComponent())
}