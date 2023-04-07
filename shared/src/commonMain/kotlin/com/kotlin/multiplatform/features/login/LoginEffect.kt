package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseEffect

sealed class LoginEffect : BaseEffect {
    object LoginSuccessful : LoginEffect()
}