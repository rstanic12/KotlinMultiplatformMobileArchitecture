package com.kotlin.multiplatform.features.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin.multiplatform.android.AppTheme
import com.kotlin.multiplatform.core.presentation.ActionDispatcher

@Composable
fun LoginLayout(
    loginViewModel: LoginViewModel,
    actionDispatcher: ActionDispatcher,
) {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                TextField(loginViewModel.email, {
                    actionDispatcher.emit(LoginAction.EmailChanged(it))
                })
                TextField(loginViewModel.password, {
                    actionDispatcher.emit(LoginAction.PasswordChanged(it))
                })
                Button({ actionDispatcher.emit(LoginAction.LoginPressed) }) {
                    Text(text = "LOGIN")
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
//    LoginLayout(
//        LoginViewModel(email = "rstanic12@gmail.com", password = "password"),
//        ActionDispatcher()
//    )
}
