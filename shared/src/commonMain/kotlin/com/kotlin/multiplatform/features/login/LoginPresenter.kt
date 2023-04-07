package com.kotlin.multiplatform.features.login

class LoginPresenter : LoginPresenterType(
    LoginViewStore(),
) {

    override suspend fun onBind() {
        collect<LoginAction> {
            onAction(it)
        }
    }

    private fun onAction(action: LoginAction) = when (action) {
        is LoginAction.EmailChanged -> render(LoginViewState.EmailChanged(action.email))
        is LoginAction.PasswordChanged -> render(LoginViewState.PasswordChanged(action.password))
        is LoginAction.LoginPressed -> trigger(LoginViewEvent.NavigateToHomeScreen)
    }
}