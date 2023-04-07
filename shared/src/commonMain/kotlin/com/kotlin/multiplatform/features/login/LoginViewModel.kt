package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseViewModel

data class LoginViewModel(
    val email: String = "",
    val password: String = ""
) : BaseViewModel