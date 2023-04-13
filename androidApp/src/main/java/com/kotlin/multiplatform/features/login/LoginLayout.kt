package com.kotlin.multiplatform.features.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun LoginLayout(component: LoginComponent) {
    val model by component.viewModel.subscribeAsState()

    Column {
        TextField(model.email, {
            component.actionDispatcher.emit(LoginAction.EmailChanged(it))
        })
        TextField(model.password, {
            component.actionDispatcher.emit(LoginAction.PasswordChanged(it))
        })
        Button(
            onClick = {
                component.actionDispatcher.emit(LoginAction.LoginPressed)
            }
        ) {
            Text(text = "LOGIN")
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    LoginLayout(PreviewLoginComponent())
}
