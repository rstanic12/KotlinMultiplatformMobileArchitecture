package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseViewState

sealed class LoginViewState : BaseViewState {
    data class EmailChanged(val email: String) : LoginViewState()
    data class PasswordChanged(val password: String) : LoginViewState()
}
