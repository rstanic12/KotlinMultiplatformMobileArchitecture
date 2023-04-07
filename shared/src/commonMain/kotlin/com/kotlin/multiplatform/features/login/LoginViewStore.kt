package com.kotlin.multiplatform.features.login

import com.kotlin.multiplatform.core.presentation.viewstore.RenderableViewStore

class LoginViewStore : RenderableViewStore<LoginViewState, LoginViewModel>(LoginViewModel()) {

    override fun reduce(viewState: LoginViewState) = when (viewState) {
        is LoginViewState.EmailChanged -> viewModel.copy(email = viewState.email)
        is LoginViewState.PasswordChanged -> viewModel.copy(password = viewState.password)
    }.apply { render(this) }
}
