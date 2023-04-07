package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.BaseViewContract

interface LoginViewContract : BaseViewContract<LoginViewModel> {
    fun navigateToHomeScreen()
}
