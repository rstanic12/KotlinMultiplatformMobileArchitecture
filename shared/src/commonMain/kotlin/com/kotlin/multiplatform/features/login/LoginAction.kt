package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseAction

sealed class LoginAction : BaseAction {
    data class EmailChanged(val email: String) : LoginAction()
    data class PasswordChanged(val password: String) : LoginAction()
    object LoginPressed : LoginAction()
}
