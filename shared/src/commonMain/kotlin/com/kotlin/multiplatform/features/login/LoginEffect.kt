package com.kotlin.multiplatform.features.login

sealed class LoginEffect {
    object LoginSuccessful : LoginEffect()
}