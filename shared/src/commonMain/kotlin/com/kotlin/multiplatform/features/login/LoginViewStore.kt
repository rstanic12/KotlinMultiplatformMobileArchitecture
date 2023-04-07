package com.kotlin.multiplatform.features.login

class LoginViewStore : LoginViewStoreType(
    LoginViewModel(),
) {
    override fun reduce(viewState: LoginViewState) {
        when (viewState) {
            is LoginViewState.EmailChanged -> viewModel.copy(email = viewState.email)
            is LoginViewState.PasswordChanged -> viewModel.copy(password = viewState.password)
        }.apply { render(this) }
    }

    override fun event(viewEvent: LoginViewEvent) {
        when (viewEvent) {
            LoginViewEvent.NavigateToHomeScreen -> viewContract.navigateToHomeScreen()
        }
    }
}
