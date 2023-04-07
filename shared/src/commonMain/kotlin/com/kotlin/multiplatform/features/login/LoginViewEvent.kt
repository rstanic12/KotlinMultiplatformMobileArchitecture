package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseViewEvent

sealed class LoginViewEvent : BaseViewEvent {
    object NavigateToHomeScreen : LoginViewEvent()
}
